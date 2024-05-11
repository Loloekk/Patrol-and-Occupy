package com.pao.game.model;

import java.util.List;
import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board{
    List<Tank> tankList;
    List<Bullet> bulletList;
    long startTime;
    long lastUpdateTime;
    int width,heigth;
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

    }
    public boolean checkBulletCollision(GameObject gameObject){
        return false;
    }
    public boolean checkTankCollision(GameObject gameObject){
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
        return heigth;
    }
}
