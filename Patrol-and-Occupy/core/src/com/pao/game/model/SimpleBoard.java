package com.pao.game.model;

import java.util.*;

import com.badlogic.gdx.math.Octree;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.Communication.ColoredParams;
import com.pao.game.Communication.Move;
import com.pao.game.Communication.Params;
import com.pao.game.viewmodel.GlobalStatistics;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class SimpleBoard implements Board {
    BoardCollider collider;
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    List<Obstacle> obstacleList = new ArrayList<>();
    List<Plate> plateList = new ArrayList<>();
    List<Dynamite> dynamiteList = new ArrayList<>();
    List<Spawn> spawnList = new ArrayList<>();
    List<BreakableObstacle> breakableObstacleList = new ArrayList<>();
    int width, height;
    Clock clock;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.collider = new BoardCollider(this);
    }

    public SimpleBoard(World world){
        this(ModelSettings.getWidth(), ModelSettings.getHeight());
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
            plateList.add(new Plate(plateParams.getX(), plateParams.getY()));
    }
    public void update(float t) {
        clock.update(t);
        Set<Bullet> bulletsToDestroy = new HashSet<>();
        Set<Map.Entry<Dynamite, ModelPlayer>> dynamitesToDestroy = new HashSet<>();
        Set<BreakableObstacle> breakableObstaclesToDestroy = new HashSet<>();
        // Revive all tanks
        List<Tank> tanksToRevive = new ArrayList<>();
        for (Tank tank : tankList)
            if(!tank.getIsAlive())
                tanksToRevive.add(tank);
        // Move every bullet
        if (bulletList != null)
            for (Bullet bullet : bulletList)
                bullet.update(t);
        // Try to move every tank
        for (Tank tank : tankList)
            tank.update(t);
        // Destroy every BreakableObstacle if Tank is nearby
        if(breakableObstacleList != null)
            for (BreakableObstacle breakableObstacle : breakableObstacleList)
                if (checkTankCollision(breakableObstacle))
                    breakableObstaclesToDestroy.add(breakableObstacle);
        // Update state of every dynamite
        if(dynamiteList != null)
            for ( Dynamite dynamite : dynamiteList)
                dynamite.update(t);

        // Check for tank collision
        for(Tank tank : tankList) {
            ModelPlayer killer = checkBulletCollision(tank);
            if(killer!= null){
                tank.kill(killer);
            }
        }
        // Check for destroyed bullets
        if (bulletList != null) {
            for (Bullet bullet : bulletList) {
                // Tank hits
                if (checkTankCollision(bullet))
                    bulletsToDestroy.add(bullet);
                // Dynamite hits
                if (checkDynamiteCollision(bullet))
                    bulletsToDestroy.add(bullet);
                // Obstacle (breakable or not) hits
                if (checkObstacleCollision(bullet) || checkBreakableObstacleCollision(bullet))
                    bulletsToDestroy.add(bullet);
                // Outside the map (currently checks if it hits map boundary)
                if (checkBoardCollision(bullet))
                    bulletsToDestroy.add(bullet);
                // Enemy spawn hits
                if (checkSpawnCollision(bullet))
                    bulletsToDestroy.add(bullet);
            }
        }
        // Check for destroyed dynamites
        if (dynamiteList != null) {
            for (Dynamite dynamite : dynamiteList) {
                // Bullet hits
                ModelPlayer killer = checkBulletCollision(dynamite);
                if (killer != null)
                    dynamitesToDestroy.add(new AbstractMap.SimpleEntry<>(dynamite, killer));
            }
        }

        // Color the plates
        for(Plate plate : plateList) {
            List<ModelPlayer> colorsSet = new ArrayList<>();
            for(Tank tank : tankList) {
                if(tank.getIsAlive() && plate.intersects(tank)) colorsSet.add(tank.getColor());
            }
            if(colorsSet.size() == 1) {
                if(plate.getColor() != null){
                    getTank(plate.getColor()).getStatistics().decrementNumberOfPlates();
                }
                plate.setColor(colorsSet.get(0));
                getTank(colorsSet.get(0)).getStatistics().incrementNumberOfPlates();
            }
            else if(colorsSet.size() > 1) plate.setColor(null);
        }
        for (Bullet bullet : bulletsToDestroy)
            bullet.destroy();
        for (Map.Entry<Dynamite, ModelPlayer> dynamite : dynamitesToDestroy)
            dynamite.getKey().destroy(dynamite.getValue());
        for (BreakableObstacle breakableObstacle : breakableObstaclesToDestroy)
            breakableObstacle.destroy(this);

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

    public void setmove(ModelPlayer color, Move move, boolean value) {
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

    public boolean checkBoardCollision(GameObject gameObject) {
        return collider.checkBoardCollision(gameObject);
    }

    public ModelPlayer checkBulletCollision(GameObject gameObject) {
        return collider.checkBulletCollision(gameObject);
    }

    public boolean checkTankCollision(GameObject gameObject) {
        return collider.checkTankCollision(gameObject);
    }

    public boolean checkObstacleCollision(GameObject gameObject) {
        return collider.checkObstacleCollision(gameObject);
    }

    public boolean checkDynamiteCollision(GameObject gameObject) {
        return collider.checkDynamiteCollision(gameObject);
    }

    public boolean checkSpawnCollision(GameObject gameObject) {
        return collider.checkSpawnCollision(gameObject);
    }
    public boolean checkBreakableObstacleCollision(GameObject gameObject){
        return collider.checkBreakableObstacleCollision(gameObject);
    }
    public List<Tank> getTankList() {
        return tankList;
    }
    public Tank getTank(ModelPlayer color){
        for(Tank tank : tankList)
        {
            if(tank.getColor() == color){
                return tank;
            }
        }
        return null;
    }
    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }
    public List<Plate> getPlateList() {
        return plateList;
    }
    public List<Dynamite> getDynamiteList() {
        return dynamiteList;
    }
    public List<Spawn> getSpawnList() {
        return spawnList;
    }
    public List<BreakableObstacle> getBreakableObstacleList() {
        return breakableObstacleList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addBullet(Bullet bullet) {
        bulletList.add(bullet);
    }

    public void addDynamite(Dynamite dynamite) {
        dynamiteList.add(dynamite);
    }
}
