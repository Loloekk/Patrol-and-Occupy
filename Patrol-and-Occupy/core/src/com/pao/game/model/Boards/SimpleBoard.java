package com.pao.game.model.Boards;

import java.util.*;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.ColoredParams;
import com.pao.game.communication.Move;
import com.pao.game.communication.Params;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.*;
import com.pao.game.model.MultiContactListener.BulletContactListener;
import com.pao.game.model.MultiContactListener.MultiContactListener;
import com.pao.game.model.MultiContactListener.TankContactListener;
import com.pao.game.viewmodel.GlobalStatistics;

import static com.pao.game.model.Constants.*;

public class SimpleBoard implements Board {
    List<BodyGameObject> bodyObjects = new ArrayList<>();
    List<DynamiteExplosion> dynamiteExplosionList = new ArrayList<>();
    List<BulletShoot> bulletShootList = new ArrayList<>();
    List<BulletExplosion> bulletExplosionList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    int width, height;
    Clock clock;
    World world;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        world = new World(GRAVITY, true);
        MultiContactListener multiContactListener = new MultiContactListener();
        world.setContactListener(multiContactListener);
        BodyCreator.setEdges(0,0, ModelSettings.getWidth(), ModelSettings.getHeight(), world);
    }
    public SimpleBoard(){
        this(ModelSettings.getWidth(), ModelSettings.getHeight());
        // Add players tanks
        Setup setup = Setup.getSetupList().get(ModelSettings.getMap());
        clock = new Clock();
        for(ModelPlayer color : ModelPlayer.getColorList(ModelSettings.getNumberOfPlayers())){
            boolean foundColor = false;
            for(ColoredParams spawnParams : setup.getSpawnParamsList()){
                if(spawnParams.getColor() == color){
                    foundColor = true;
                    Tank tank = new Tank(spawnParams.getX(), spawnParams.getY(), color, this);
                    bodyObjects.add(tank);
                    tankList.add(tank);
                    break;
                }
            }
            if(!foundColor)
                throw new RuntimeException("Setup does not contain info about all players");
        }
        // Add obstacles
        for(Params obstacleParams : setup.getObstacleList())
            bodyObjects.add(new UnbreakableObstacle(obstacleParams.getX(), obstacleParams.getY(), obstacleParams.getWidth(), obstacleParams.getHeight(), obstacleParams.getRotation(), world));
        // Add breakable obstacles
        for(Params breakableObstacleParams : setup.getBreakableObstacleList())
            bodyObjects.add(new BreakableObstacle(breakableObstacleParams.getX(), breakableObstacleParams.getY(), breakableObstacleParams.getWidth(), breakableObstacleParams.getHeight(), breakableObstacleParams.getRotation(), world));
        // Add plates
        for(Params plateParams : setup.getPlateList())
            bodyObjects.add(new Plate(plateParams.getX(), plateParams.getY(),this));
    }
    public void update(float time) {
        world.step((time), VELOCITY_ITERATION, POSITION_ITERATION);
        clock.update(time);
        // Update tanks
        for(BodyGameObject obj : new ArrayList<>(bodyObjects))
        {
            if(obj.getIsActive()){
                obj.update(time);
            }
        }
        List<BodyGameObject> objectToDestroy = new ArrayList<>();
        for(BodyGameObject obj : bodyObjects)
        {
            if(!obj.getIsActive()){
                objectToDestroy.add(obj);
                world.destroyBody(obj.getBody());
            }
        }
        bodyObjects.removeAll(objectToDestroy);
//        List<Tank> tanksToRevive = new ArrayList<>();
//        for (Tank tank : bodyObjects) {
//            tank.update(time);
//            if (!tank.getIsAlive()) {
//                tanksToRevive.add(tank);
//            }
//        }
//        // Update bullets
//        List<Bullet> bulletsToDestroy = new ArrayList<>();
//        for (Bullet bullet : bodyObjects) {
//            bullet.update(time);
//            if(!bullet.getIsAlive()) {
//                bulletsToDestroy.add(bullet);
//                world.destroyBody(bullet.getBody());
//            }
//        }
//        bodyObjects.removeAll(bulletsToDestroy);
        // Update explosions
        List<DynamiteExplosion> dynamiteExplosionsToDestroy = new ArrayList<>();
        for(DynamiteExplosion dynamiteExplosion : dynamiteExplosionList) {
            dynamiteExplosion.update(time);
            if(dynamiteExplosion.isFinished()) {
                dynamiteExplosionsToDestroy.add(dynamiteExplosion);
            }
        }
        dynamiteExplosionList.removeAll(dynamiteExplosionsToDestroy);
        // Update explosions
        List<BulletShoot> bulletShootsToDestroy = new ArrayList<>();
        for(BulletShoot bulletShoot : bulletShootList) {
            bulletShoot.update(time);
            if(bulletShoot.isFinished()) {
                bulletShootsToDestroy.add(bulletShoot);
            }
        }
        bulletShootList.removeAll(bulletShootsToDestroy);
        // Update explosions
        List<BulletExplosion> bulletExplosionsToDestroy = new ArrayList<>();
        for(BulletExplosion bulletExplosion : bulletExplosionList) {
            bulletExplosion.update(time);
            if(bulletExplosion.isFinished()) {
                bulletExplosionsToDestroy.add(bulletExplosion);
            }
        }
        bulletExplosionList.removeAll(bulletExplosionsToDestroy);
//        // Update dynamites
//        List<Dynamite> dynamitesToDestroy = new ArrayList<>();
//        for (Dynamite dynamite : bodyObjects) {
//            dynamite.update(time);
//            if(!dynamite.getIsAlive()) {
//                dynamitesToDestroy.add(dynamite);
//                world.destroyBody(dynamite.getBody());
//            }
//        }
//        bodyObjects.removeAll(dynamitesToDestroy);
//        // Destroy breakableObstacles
//        List<BreakableObstacle> breakableObstaclesToDestroy = new ArrayList<>();
//        for(BreakableObstacle breakableObstacle : bodyObjects) {
//            if(!breakableObstacle.getIsAlive()) {
//                breakableObstaclesToDestroy.add(breakableObstacle);
//                world.destroyBody(breakableObstacle.getBody());
//            }
//        }
//        bodyObjects.removeAll(breakableObstaclesToDestroy);
//
//        Setup setup = Setup.getSetupList().get(ModelSettings.getMap());
//        for (Tank tank : tanksToRevive) {
//            for (ColoredParams spawnParams : setup.getSpawnParamsList()) {
//                if (tank.getColor() == spawnParams.getColor()) {
//                    tank.setPosition(spawnParams.getX(), spawnParams.getY());
//                    tank.revive();
//                    break;
//                }
//            }
//        }
    }
    @Override
    public float getRemainingTime() {
        return clock.getRemainingTime();
    }
    public void setMove(ModelPlayer color, Move move, boolean value) {
        Tank tank = null;
        for (Tank t : tankList) {
            if (color == t.getColor()) {
                tank = t;
                break;
            }
        }
        switch (move) {
            case Forward:
                tank.setMoveForwardState(value);
                break;
            case Back:
                tank.setMoveBackwardsState(value);
                break;
            case Left:
                tank.setMoveLeftState(value);
                break;
            case Right:
                tank.setMoveRightState(value);
                break;
            case Shoot:
                tank.setMakeShoot(value);
                break;
            case Dynamite:
                tank.setPlaceDynamite(value);
                break;
        }
    }
    public List<BodyGameObject> getBodyObjects() { return bodyObjects; }
    public List<Tank> getTankList() { return tankList; }
    public Tank getTank(ModelPlayer color){
        return tankList.stream()
                .filter(tank -> tank.getColor() == color)
                .findFirst()
                .orElse(null);
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public World getWorld(){return world; }
    public void addObject(BodyGameObject obj) { bodyObjects.add(obj); }
    public void addDynamiteExplosion(DynamiteExplosion dynamiteExplosion) { dynamiteExplosionList.add(dynamiteExplosion); }
    public void addBulletShoot(BulletShoot bulletShoot) { bulletShootList.add(bulletShoot); }
    public void addBulletExplosion(BulletExplosion bulletExplosion) { bulletExplosionList.add(bulletExplosion); }
    public List<DynamiteExplosion> getDynamiteExplosionList() { return dynamiteExplosionList; }
    public List<BulletShoot> getBulletShootList() { return bulletShootList; }
    public List<BulletExplosion> getBulletExplosionList() { return bulletExplosionList; }
}