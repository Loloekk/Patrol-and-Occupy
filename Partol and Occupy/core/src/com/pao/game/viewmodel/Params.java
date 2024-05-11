package com.pao.game.viewmodel;

public class Params {
    float widht;
    float height;
    float x;
    float y;
    float rotation;
    public Params(float widht, float height, float x, float y, float rotation){
        this.widht=widht;
        this.height=height;
        this.x=x;
        this.y=y;
        this.rotation=rotation;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidht() {
        return widht;
    }
    public float getHeight() {
        return height;
    }
    public float getRotation(){
        return rotation;
    }
}
