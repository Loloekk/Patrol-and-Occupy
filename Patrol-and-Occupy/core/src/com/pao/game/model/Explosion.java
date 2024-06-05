package com.pao.game.model;

public class Explosion {
    static final float width = 300;
    static final float height = 300;
    float x, y;
    float stateTime;
    public boolean finished = false;
    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;
        stateTime = 0;
    }

    public void update(float time) {
        stateTime += time;
        if(stateTime >= 0.9f) finished = true;
    }
    public boolean isFinished() { return finished; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getStateTime() { return stateTime; }
}
