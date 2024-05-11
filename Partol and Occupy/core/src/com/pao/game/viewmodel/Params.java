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
    float getX() {
        return x;
    }
    float getY() {
        return y;
    }
    float getWidht() {
        return widht;
    }
    float getHeight() {
        return height;
    }
    float getRotation(){
        return rotation;
    }
}
