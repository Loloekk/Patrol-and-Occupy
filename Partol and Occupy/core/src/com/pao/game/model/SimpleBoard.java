package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board{
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    long startTime;
    long lastUpdateTime;
    int width,height;
    SimpleBoard(int width, int height){
        this.width = width;
        this.height = height;
        setstart(System.nanoTime());
        lastUpdateTime = startTime;
    }
    public void setstart(long time){
        startTime = time;
    }
    public void update(long t){
        // Try to move every tank
        for(Tank tank : tankList)
            tank.update(t);
        // Move every bullet
        for(Bullet bullet : bulletList)
            bullet.update(t);
        // Check for tank bullet hits (nothing, it's checked in Tank.update())
        // Check for destroyed bullets
        {
            Set<Bullet> toDestroy = new HashSet<>();
            // Tank hits
            for(Bullet bullet : bulletList)
                if(checkTankCollision(bullet))
                    toDestroy.add(bullet);
            // Outside the map (currently checks if it hits map boundary)
            for(Bullet bullet : bulletList)
                if(checkBoardCollision(bullet))
                    toDestroy.add(bullet);
            for(Bullet bullet : toDestroy)
                bullet.destroy();
        }
        lastUpdateTime = System.nanoTime();
    }
    public void setmove(Color color, Move move, boolean value){
        Tank tank = null;
        for(Tank t : tankList){
            if(color == t.getColor()){
                tank = t;
                break;
            }
        }
        if(tank == null){
            throw new RuntimeException("Unknown color");
        }
        switch(move){
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
        }
    }
    public boolean checkBoardCollision(GameObject gameObject){
        float[] Vertices = gameObject.polygon.getVertices();
        for(int i=0; i<Vertices.length; i+=2){
            float X = Vertices[i];
            float Y = Vertices[i+1];
            if(X>=width || X<=0 || Y>=height || Y<=0)
                return true;
        }
        return false;
    }
    public boolean checkBulletCollision(GameObject gameObject){
        for(Bullet bullet : getBulletList())
            if(gameObject.intersects(bullet))
                return true;
        return false;
    }
    public boolean checkTankCollision(GameObject gameObject){
        for(Tank tank : getTankList())
            if(gameObject.intersects(tank))
                return true;
        return false;
    }
    public List<Tank> getTankList(){
        return tankList;
    }
    public List<Bullet> getBulletList(){
        return bulletList;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void addBullet(Bullet bullet){
        bulletList.add(bullet);
    }
}
