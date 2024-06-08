package com.pao.game.communication;

public class ExplosionParams extends Params{
    float stateTime;
    public ExplosionParams(float width, float height, float x, float y, float rotation, float stateTime) {
        super(width, height, x, y, rotation);
        this.stateTime = stateTime;
    }
    public float getStateTime() { return stateTime; }
}
