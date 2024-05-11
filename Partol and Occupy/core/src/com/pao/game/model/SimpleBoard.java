package com.pao.game.model;

import java.util.List;
import com.pao.game.viewmodel.*;

import static java.lang.Math.abs;

public class SimpleBoard implements Board{
    List<Tank> tankList;
    List<Bullet> bulletList;
    long startTime;
    long lastUpdateTime;
    int width,height;
    public void setstart(long time){
        startTime = time;
    }
    public void update(long t){

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
}
