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
                    throw new RuntimeException("Unknown color in SimpleBoard() constructor");
                }
            }
        }
    }
    public void setstart(float time){
        startTime = time;
    }
    public void update(float t){
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
        System.out.println("WIDTH:"+width+" HEIGHT:"+height);
        for(int i=0; i<Vertices.length; i+=2){
            float X = Vertices[i];
            float Y = Vertices[i+1];
            System.out.println("X:"+X+" Y:"+Y);
            if(X>=width || X<=0 || Y>=height || Y<=0)
                return true;
        }
        return false;
    }
    public boolean checkBulletCollision(GameObject gameObject){
        for(Bullet bullet : getBulletList())
            if(gameObject!=bullet && gameObject.intersects(bullet))
                return true;
        return false;
    }
    public boolean checkTankCollision(GameObject gameObject){
        for(Tank tank : getTankList())
            if(gameObject!=tank && gameObject.intersects(tank))
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
