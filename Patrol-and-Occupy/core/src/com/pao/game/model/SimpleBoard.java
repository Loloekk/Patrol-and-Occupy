package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board{
    List<Tank> tankList = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<>();
    float startTime;
    float lastUpdateTime;
    int width,height;
    public SimpleBoard(int width, int height){
        this.width = width;
        this.height = height;
        setstart((float)System.nanoTime() * (float)1e9);
        lastUpdateTime = startTime;
    }
    public SimpleBoard(int width, int height, List<Color> players){
        this(width,height);
        for(Color color : players){
            final float offX = 200;
            final float offY = 200;
            switch(color){
                case R: tankList.add(new Tank(offX,offY,Color.R,this)); break;
                case G: tankList.add(new Tank(offX,height-offY,Color.G,this)); break;
                case B: tankList.add(new Tank(width-offX,height-offY,Color.B,this)); break;
                case Y: tankList.add(new Tank(width-offX,offY,Color.Y,this)); break;
                default:{
                    throw new RuntimeException("Unknown color");
                }
            }
        }
    }
    public void setstart(float time){
        startTime = time;
    }
    public void update(float t){
        // Move every bullet
        if(bulletList != null){
            for (Bullet bullet : bulletList)
                bullet.update(t);
        }
        // Try to move every tank
        if(tankList != null) {
            for (Tank tank : tankList)
                tank.update(t);
        }
        // Check for tank bullet hits (nothing, it's checked in Tank.update())
        // Check for destroyed bullets
        {
            Set<Bullet> toDestroy = new HashSet<>();
            // Tank hits
            if(bulletList != null) {
                for (Bullet bullet : bulletList)
                    if (checkTankCollision(bullet))
                        toDestroy.add(bullet);
            }
            // Outside the map (currently checks if it hits map boundary)
            if(bulletList != null) {
                for (Bullet bullet : bulletList)
                    if (checkBoardCollision(bullet))
                        toDestroy.add(bullet);
            }
            for(Bullet bullet : toDestroy)
                bullet.destroy();
        }
        lastUpdateTime = System.nanoTime() * (float)1e9;
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
        float[] Vertices = gameObject.polygon.getTransformedVertices();
        final int offset = 6;
        for(int i=0; i<Vertices.length; i+=2){
            float X = Vertices[i];
            float Y = Vertices[i+1];
            if(X>=width+offset || X<=-offset || Y>=height+offset || Y<=-offset)
                return true;
        }
        return false;
    }
    public boolean checkBulletCollision(GameObject gameObject){
        if(getBulletList() == null)
            return false;
        for(Bullet bullet : getBulletList())
            if(gameObject!=bullet && gameObject.intersects(bullet) && !(gameObject instanceof Tank && ((Tank) gameObject).color==bullet.color)){
                return true;
            }
        return false;
    }
    public boolean checkTankCollision(GameObject gameObject){
        if(getTankList() == null)
            return false;
        for(Tank tank : getTankList())
            if(gameObject!=tank && gameObject.intersects(tank) && !(gameObject instanceof Bullet && ((Bullet) gameObject).color==tank.color))
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
    public void shoot(Color color){
        if(tankList == null)
            return;
        for(Tank tank : tankList){
            if(tank.color == color){
                tank.shoot();
                return;
            }
        }
        throw new RuntimeException("Unknown color");
    }
}
