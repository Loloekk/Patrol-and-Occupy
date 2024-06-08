package com.pao.game.model;

public class Explosion {
    float width;
    float height;
    float x, y;
    float rotation;
    float stateTime;
    float animationTime;
    public boolean finished = false;
    public Explosion(float x, float y, float width, float height, float rotation, float animationTime) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.animationTime = animationTime;
        stateTime = 0;
    }

    public void update(float time) {
        stateTime += time;
        if(stateTime >= animationTime) finished = true;
    }
    public boolean isFinished() { return finished; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getRotation() { return rotation; }
    public float getStateTime() { return stateTime; }
}
