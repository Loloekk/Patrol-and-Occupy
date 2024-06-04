package com.pao.game.communication;

public class Params {
    float width;
    float height;
    float x;
    float y;
    float rotation;
    public Params(float width, float height, float x, float y, float rotation){
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.rotation=rotation;
    }
    public Params(float x, float y){
        this.x=x;
        this.y=y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getRotation(){
        return rotation;
    }
}