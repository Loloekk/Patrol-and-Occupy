package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board {
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    List<Obstacle> obstacleList = new ArrayList<>();
    float remainingTime;
    int width, height;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        remainingTime = 60;
    }

    public SimpleBoard(int width, int height, List<MyColor> players) {
        this(width, height);
        for (MyColor color : players) {
            final float offX = 200;
            final float offY = 200;
            switch (color) {
                case R:
                    tankList.add(new Tank(offX, offY, MyColor.R, this));
                    break;
                case G:
                    tankList.add(new Tank(offX, height - offY, MyColor.G, this));
                    break;
                case B:
                    tankList.add(new Tank(width - offX, height - offY, MyColor.B, this));
                    break;
                case Y:
                    tankList.add(new Tank(width - offX, offY, MyColor.Y, this));
                    break;
                default: {
                    throw new RuntimeException("Unknown color");
                }
            }
        }
        obstacleList.addAll(Obstacle.rectangleObstacle(400, 700, 50, 1, 6, 0));
        obstacleList.addAll(Obstacle.rectangleObstacle(400, 400, 50, 4, 1, 0));
        obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0));
        obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0));
        obstacleList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0));
    }

    public SimpleBoard(int width, int height, List<MyColor> players, Setup setup){
        this(width, height);
        // Add players tanks
        for(MyColor color : players){
            boolean foundColor = false;
            for(ColoredParams tankParams : setup.getTankParamsList()){
                if(tankParams.getColor() == color){
                    foundColor = true;
                    tankList.add(new Tank(tankParams.getX(), tankParams.getY(), color, this));
                    break;
                }
            }
            if(!foundColor)
                throw new RuntimeException("Setup does not contain info about all players");
        }
        // Add obstacles
        obstacleList.addAll(setup.getObstacleList());
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
        {
            Set<Bullet> toDestroy = new HashSet<>();
            // Tank hits
            if (bulletList != null) {
                for (Bullet bullet : bulletList)
                    if (checkTankCollision(bullet))
                        toDestroy.add(bullet);
            }
            // Obstacle hits
            if (bulletList != null) {
                for (Bullet bullet : bulletList)
                    if (checkObstacleCollision(bullet))
                        toDestroy.add(bullet);
            }
            // Outside the map (currently checks if it hits map boundary)
            if (bulletList != null) {
                for (Bullet bullet : bulletList)
                    if (checkBoardCollision(bullet))
                        toDestroy.add(bullet);
            }
            for (Bullet bullet : toDestroy)
                bullet.destroy();
        }
    }

    public void setmove(MyColor color, Move move, boolean value) {
        Tank tank = null;
        for (Tank t : tankList) {
            if (color == t.getColor()) {
                tank = t;
                break;
            }
        }
        if (tank == null) {
            throw new RuntimeException("Unknown color");
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

    public List<Tank> getTankList() {
        return tankList;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
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
}
