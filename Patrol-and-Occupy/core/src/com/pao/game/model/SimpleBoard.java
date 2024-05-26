package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.Communication.ColoredParams;
import com.pao.game.Communication.Move;
import com.pao.game.Communication.Params;

public class SimpleBoard implements Board {
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    List<Obstacle> obstacleList = new ArrayList<>();
    List<Plate> plateList = new ArrayList<>();
    List<Dynamite> dynamiteList = new ArrayList<>();
    List<Spawn> spawnList = new ArrayList<>();
    float remainingTime;
    int width, height;
    ModelSettings settings;
    Clock clock;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SimpleBoard(ModelSettings settings, World world){
        this(settings.getWidth(), settings.getHeight());
        this.settings = settings;
        // Add players tanks
        Setup setup = Setup.getSetupList().get(settings.getMap());
        clock = new Clock(settings);
        for(ModelPlayer color : ModelPlayer.getColorList(settings.getNumberOfPlayers())){
            boolean foundColor = false;
            for(ColoredParams spawnParams : setup.getSpawnParamsList()){
                if(spawnParams.getColor() == color){
                    foundColor = true;
                    spawnList.add(new Spawn(spawnParams.getX(), spawnParams.getY(), color));
                    tankList.add(new Tank(spawnParams.getX(), spawnParams.getY(), color, this, world,settings));
                    break;
                }
            }
            if(!foundColor)
                throw new RuntimeException("Setup does not contain info about all players");
        }
        // Add obstacles
        for(Params obstacleParams : setup.getObstacleList()) {
            obstacleList.add(new Obstacle(obstacleParams.getX(), obstacleParams.getY(), obstacleParams.getWidht(), obstacleParams.getHeight(), obstacleParams.getRotation(), world));
        }
        // Add plates
        for(Params plateParams : setup.getPlateList()){
            plateList.add(new Plate(plateParams.getX(), plateParams.getY()));
        }
    }

    public void setRemainingTime(float time) {
        remainingTime = time;
    }
    public void addRemainingTime(float time) {
        remainingTime += time;
    }
    public void update(float t) {
        clock.update(t);
        Set<Bullet> bulletsToDestroy = new HashSet<>();
        Set<Dynamite> dynamitesToDestroy = new HashSet<>();
        // Revive all tanks
        List<Tank> tanksToRevive = new ArrayList<>();
        for (Tank tank : tankList)
            if(!tank.getIsAlive())
                tanksToRevive.add(tank);

        // Move every bullet
        if (bulletList != null) {
            for (Bullet bullet : bulletList)
                bullet.update(t);
        }
        // Try to move every tank
        if (tankList != null) {
            for (Tank tank : tankList)
                tank.update(t);
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
                // Obstacle hits
                if (checkObstacleCollision(bullet))
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
                if (checkBulletCollision(dynamite))
                    dynamitesToDestroy.add(dynamite);
            }
        }

        // Color the plates
        for(Plate plate : plateList) {
            List<ModelPlayer> colorsSet = new ArrayList<>();
            for(Tank tank : tankList) {
                if(tank.getIsAlive() && plate.intersects(tank)) colorsSet.add(tank.getColor());
            }
            if(colorsSet.size() == 1) plate.setColor(colorsSet.get(0));
            else if(colorsSet.size() > 1) plate.setColor(null);
        }
        for (Bullet bullet : bulletsToDestroy)
            bullet.destroy();
        for (Dynamite dynamite : dynamitesToDestroy)
            dynamite.destroy();
        Setup setup = Setup.getSetupList().get(settings.getMap());
        for (Tank tank : tanksToRevive) {
            for (ColoredParams spawnParams : setup.getSpawnParamsList()) {
                if (tank.getColor() == spawnParams.getColor()) {
                    tank.setPosition(spawnParams.getX(), spawnParams.getY());
                    tank.setIsAlive(true);
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
        float[] Vertices = gameObject.getVertices();
        final int offset = 6;
        for (int i = 0; i < Vertices.length; i += 2) {
            float X = Vertices[i];
            float Y = Vertices[i + 1];
            if (X >= width + offset || X <= -offset || Y >= height + offset || Y <= -offset)
                return true;
        }
        return false;
    }

    public boolean checkBulletCollision(GameObject gameObject) {
        if (getBulletList() == null)
            return false;
        for (Bullet bullet : getBulletList())
            if (gameObject != bullet && gameObject.intersects(bullet) && !(gameObject instanceof Tank && ((Tank) gameObject).color == bullet.color)) {
                return true;
            }
        return false;
    }

    public boolean checkTankCollision(GameObject gameObject) {
        if (getTankList() == null)
            return false;
        for (Tank tank : getTankList())
            if (gameObject != tank && gameObject.intersects(tank) && !(gameObject instanceof Bullet && ((Bullet) gameObject).color == tank.color))
                return true;
        return false;
    }

    public boolean checkObstacleCollision(GameObject gameObject) {
        if (getObstacleList() == null)
            return false;
        for (Obstacle obstacle : getObstacleList())
            if (gameObject != obstacle && gameObject.intersects(obstacle))
                return true;
        return false;
    }

    public boolean checkDynamiteCollision(GameObject gameObject) {
        if (getObstacleList() == null)
            return false;
        for (Dynamite dynamite : getDynamiteList())
            if (gameObject != dynamite && gameObject.intersects(dynamite))
                return true;
        return false;
    }

    public boolean checkSpawnCollision(GameObject gameObject) {
        if(getSpawnList() == null)
            return false;
        for (Spawn spawn : getSpawnList())
            if (gameObject != spawn && gameObject.intersects(spawn)
                    && !(gameObject instanceof Tank && ((Tank)gameObject).getColor()==spawn.getColor())
                    && !(gameObject instanceof Bullet && ((Bullet)gameObject).getColor()==spawn.getColor()))
                return true;
        return false;
    }
    public List<Tank> getTankList() {
        return tankList;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }
    public List<Plate> getPlateList() { return plateList; }
    public List<Dynamite> getDynamiteList() { return dynamiteList; }
    public List<Spawn> getSpawnList() { return spawnList; }

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
