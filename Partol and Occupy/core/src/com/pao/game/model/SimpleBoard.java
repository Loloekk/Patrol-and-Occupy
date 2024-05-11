package com.pao.game.model;

import com.badlogic.gdx.Game;

import java.util.List;

public class SimpleBoard implements Board{
    List<Tank> tankList;
    List<Bullet> bulletList;
    long startTime;
    long lastUpdateTime;
    public void setStartTime(){}
    public void setstart(long time){}
    public void update(long t){}
    public void setmove(color c, int move, boolean value){}
    public boolean checkBoardCollision(GameObject board){
        return false;
    }
    public boolean checkBulletCollision(GameObject bullet){
        return false;
    }
    public boolean checkTankCollision(GameObject tank){
        return false;
    }

}
