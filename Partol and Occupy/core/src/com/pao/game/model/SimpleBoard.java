package com.pao.game.model;

import java.util.List;
import com.pao.game.viewmodel.*;

public class SimpleBoard implements Board{
    List<Tank> tankList;
    List<Bullet> bulletList;
    long startTime;
    long lastUpdateTime;
    int width,heigth;
    public void setStartTime(){}
    public void setstart(long time){}
    public void update(long t){}
    public void setmove(Color color, Move move, boolean value){}
    public boolean checkBoardCollision(GameObject gameObject){
        return false;
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
