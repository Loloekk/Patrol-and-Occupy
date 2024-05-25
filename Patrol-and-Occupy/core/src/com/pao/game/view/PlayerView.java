package com.pao.game.view;

import com.pao.game.model.ModelPlayer;

public class PlayerView {
    ModelPlayer color;
    int up;
    int down;
    int left;
    int right;
    int shoot;
    int placeDynamite;
    boolean lastShoot;
    boolean lastStateUp;
    boolean lastStateDown;
    boolean lastStateLeft;
    boolean lastStateRight;
    boolean lastPlaceDynamite;
    public ModelPlayer getColor(){
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
    public int getPlaceDynamite(){ return placeDynamite; }
    public void setLastShoot(boolean state){
        lastShoot = state;
    }
    public void setLastStateUp(boolean state){
        lastStateUp = state;
    }
    public void setLastStateDown(boolean state){
        lastStateDown = state;
    }
    public void setLastStateLeft(boolean state){
        lastStateLeft = state;
    }
    public void setLastStateRight(boolean state){
        lastStateRight = state;
    }
    public void setLastPlaceDynamite(boolean state) { lastPlaceDynamite = state; }
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
    public boolean getLastPlaceDynamite(){ return lastPlaceDynamite; }
    public PlayerView(ModelPlayer color, int up, int down, int left, int right, int shot, int placeDynamite)
    {
        this.color =color;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shot;
        this.placeDynamite = placeDynamite;
        lastStateDown = false;
        lastStateUp = false;
        lastStateLeft = false;
        lastStateRight = false;
        lastShoot = false;
        lastPlaceDynamite = false;
    }
}