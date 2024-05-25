package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board {
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    List<Obstacle> obstacleList = new ArrayList<>();
    List<Plate> plateList = new ArrayList<>();
    List<Dynamite> dynamiteList = new ArrayList<>();
    float remainingTime;
    int width, height;
    ModelSettings settings;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        remainingTime = 60;
    }

    public SimpleBoard(ModelSettings settings, World world){
        this(settings.getWidth(), settings.getHeight());
        this.settings = settings;
        // Add players tanks
        Setup setup = Setup.getSetupList().get(settings.getMap());

        for(MyColor color : MyColor.getColorList(settings.getNumberOfPlayers())){
            boolean foundColor = false;
            for(ColoredParams tankParams : setup.getTankParamsList()){
                if(tankParams.getColor() == color){
                    foundColor = true;
                    tankList.add(new Tank(tankParams.getX(), tankParams.getY(), color, this, world,settings));
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
        plateList.addAll(setup.getPlateList());
    }

    public void setRemainingTime(float time) {
        remainingTime = time;
    }
    public void addRemainingTime(float time) {
        remainingTime += time;
    }
    public float getRemainingTime(){
        return remainingTime;
    }
    public void update(float t) {
        Set<Bullet> bulletsToDestroy = new HashSet<>();
        Set<Dynamite> dynamitesToDestroy = new HashSet<>();
        remainingTime-=t;
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
            List<MyColor> colorsSet = new ArrayList<>();
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
    }

    public void setmove(MyColor color, Move move, boolean value) {
        Tank tank = null;
        for (Tank t : tankList) {
            if (color == t.getColor()) {
                tank = t;
                break;
            }
        }
        switch (move) {
            case F:
                tank.setMoveForwardState(value);
                break;
            case B:
                tank.setMoveBackwardsState(value);
                break;
            case L:
                tank.setMoveLeftState(value);
                break;
            case R:
                tank.setMoveRightState(value);
                break;
            case S:
                tank.setMakeShoot(value);
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
    public List<Dynamite> getDynamiteList(){ return dynamiteList; }

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
