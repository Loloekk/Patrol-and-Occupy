package com.pao.game.model.Boards;

import java.util.*;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.ColoredParams;
import com.pao.game.communication.Move;
import com.pao.game.communication.Params;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.*;

public class SimpleBoard implements Board {
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    List<Obstacle> obstacleList = new ArrayList<>();
    List<Plate> plateList = new ArrayList<>();
    List<Dynamite> dynamiteList = new ArrayList<>();
    List<Spawn> spawnList = new ArrayList<>();
    List<BreakableObstacle> breakableObstacleList = new ArrayList<>();
    int width, height;
    Clock clock;
    World world;
    List<Map.Entry<Tank, ModelPlayer>> tanksToDestroy = new ArrayList<>();
    List<Bullet> bulletsToDestroy = new ArrayList<>();
    List<BreakableObstacle> breakableObstaclesToDestroy = new ArrayList<>();
    List<Map.Entry<Dynamite, ModelPlayer>> dynamitesToDestroy = new ArrayList<>();

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public SimpleBoard(World world){
        this(ModelSettings.getWidth(), ModelSettings.getHeight());
        this.world = world;
        // Add players tanks
        Setup setup = Setup.getSetupList().get(ModelSettings.getMap());
        clock = new Clock();
        for(ModelPlayer color : ModelPlayer.getColorList(ModelSettings.getNumberOfPlayers())){
            boolean foundColor = false;
            for(ColoredParams spawnParams : setup.getSpawnParamsList()){
                if(spawnParams.getColor() == color){
                    foundColor = true;
                    spawnList.add(new Spawn(spawnParams.getX(), spawnParams.getY(), color, world));
                    tankList.add(new Tank(spawnParams.getX(), spawnParams.getY(), color, this, world));
                    break;
                }
            }
            if(!foundColor)
                throw new RuntimeException("Setup does not contain info about all players");
        }
        // Add obstacles
        for(Params obstacleParams : setup.getObstacleList())
            obstacleList.add(new Obstacle(obstacleParams.getX(), obstacleParams.getY(), obstacleParams.getWidht(), obstacleParams.getHeight(), obstacleParams.getRotation(), world));
        // Add breakable obstacles
        for(Params breakableObstacleParams : setup.getBreakableObstacleList())
            breakableObstacleList.add(new BreakableObstacle(breakableObstacleParams.getX(), breakableObstacleParams.getY(), breakableObstacleParams.getWidht(), breakableObstacleParams.getHeight(), breakableObstacleParams.getRotation(), world));
        // Add plates
        for(Params plateParams : setup.getPlateList())
            plateList.add(new Plate(plateParams.getX(), plateParams.getY(), world));
    }
    public void update(float t) {
        clock.update(t);
        // Revive all tanks
        List<Tank> tanksToRevive = new ArrayList<>();
        for (Tank tank : tankList)
            if(!tank.getIsAlive())
                tanksToRevive.add(tank);
        // Move every bullet
        for (Bullet bullet : bulletList)
            bullet.update(t);
        // Try to move every tank
        for (Tank tank : tankList)
            tank.update(t);
        // Update state of every dynamite
        for (Dynamite dynamite : dynamiteList)
            dynamite.update(t);

        // Destroy tanks
        for(Map.Entry<Tank, ModelPlayer> tank : tanksToDestroy) {
            tank.getKey().kill(tank.getValue());
        }
        tanksToDestroy.clear();
        // Destroy bullets
        for(Bullet bullet : bulletsToDestroy) {
            world.destroyBody(bullet.getBody());
            bulletList.remove(bullet);
        }
        bulletsToDestroy.clear();
        // Destroy dynamites
        for (Map.Entry<Dynamite, ModelPlayer> dynamite : dynamitesToDestroy) {
            dynamite.getKey().destroy(dynamite.getValue());
        }
        dynamitesToDestroy.clear();
        // Destroy breakableObstacles
        for(BreakableObstacle breakableObstacle : breakableObstaclesToDestroy) {
            world.destroyBody(breakableObstacle.getBody());
            breakableObstacleList.remove(breakableObstacle);
        }
        breakableObstaclesToDestroy.clear();

        Setup setup = Setup.getSetupList().get(ModelSettings.getMap());
        for (Tank tank : tanksToRevive) {
            for (ColoredParams spawnParams : setup.getSpawnParamsList()) {
                if (tank.getColor() == spawnParams.getColor()) {
                    tank.setPosition(spawnParams.getX(), spawnParams.getY());
                    tank.revive();
                    break;
                }
            }
        }
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
    public List<Tank> getTankList() { return tankList; }
    public Tank getTank(ModelPlayer color){
        return tankList.stream()
                .filter(tank -> tank.getColor() == color)
                .findFirst()
                .orElse(null);
    }
    public List<Bullet> getBulletList() { return bulletList; }
    public List<Obstacle> getObstacleList() { return obstacleList; }
    public List<Plate> getPlateList() { return plateList; }
    public List<Dynamite> getDynamiteList() { return dynamiteList; }
    public List<Spawn> getSpawnList() { return spawnList; }
    public List<BreakableObstacle> getBreakableObstacleList() { return breakableObstacleList; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void addBullet(Bullet bullet) { bulletList.add(bullet); }
    public void addDynamite(Dynamite dynamite) { dynamiteList.add(dynamite); }
    public void destroyTank(Tank tank, ModelPlayer killer) { tanksToDestroy.add(new AbstractMap.SimpleEntry<>(tank, killer)); }
    public void destroyBullet(Bullet bullet) { bulletsToDestroy.add(bullet); }
    public void destroyBreakableObstacle(BreakableObstacle breakableObstacle) { breakableObstaclesToDestroy.add(breakableObstacle); }
    public void destroyDynamite(Dynamite dynamite, ModelPlayer killer) { dynamitesToDestroy.add(new AbstractMap.SimpleEntry<>(dynamite, killer)); }
    public void changePlateOwner(Plate plate, Tank owner) {
        if(owner.getIsAlive()) {
            if(plate.getColor() != null) {
                getTank(plate.getColor()).getStatistics().decrementNumberOfPlates();
            }
            plate.setColor(owner.getColor());
            owner.getStatistics().incrementNumberOfPlates();
        }
    }
}