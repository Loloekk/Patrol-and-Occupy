package com.pao.game.view;

import com.pao.game.viewmodel.MyColor;

public class PlayerView {
    MyColor color;
    int up;
    int down;
    int left;
    int right;
    int shoot;
    boolean lastShoot;
    boolean lastStateUp;
    boolean lastStateDown;
    boolean lastStateLeft;
    boolean lastStateRight;
    public MyColor getColor(){
        return color;
    }
    public void setUp(int value){
         up = value;
    }
    public int getUp(){
         return up;
    }
    public void setDown(int value){
         down= value;
    }
    public int getDown(){
        return down;
    }
    public void setLeft(int value){
         left = value;
    }
    public int getLeft(){
        return left;
    }
    public void setRight(int value){
         right = value;
    }
    public int getRight(){
        return right;
    }
    public void setShoot(int value){
         shoot = value;
    }
    public int getShoot(){
        return shoot;
    }
    public void setLastShoot(boolean state){
        lastShoot= state;
    }
    public void setLastStateUp(boolean state){
        lastStateUp= state;
    }
    public void setLastStateDown(boolean state){
        lastStateDown= state;
    }
    public void setLastStateLeft(boolean state){
        lastStateLeft= state;
    }
    public void setLastStateRight(boolean state){
        lastStateRight= state;
    }
    public boolean getLastShoot(){
        return lastShoot;
    }
    public boolean getLastStateUp(){
        return lastStateUp;
    }
    public boolean getLastStateDown(){
        return lastStateDown;
    }
    public boolean getLetLastStateLeft(){
        return lastStateLeft;
    }
    public boolean getLastStateRight(){
        return lastStateRight;
    }
    public PlayerView(MyColor color, int up,int down,int left,int right, int shot)
    {
        this.color =color;
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
        this.shoot=shot;
        lastStateDown = false;
        lastStateUp = false;
        lastStateLeft = false;
        lastStateRight = false;
        lastShoot = false;
    }
}