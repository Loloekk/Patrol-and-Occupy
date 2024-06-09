package com.pao.game.communication.Descriptions;

public abstract class ExplosionDescription extends ObjectDescription{
    float stateTime;
    public ExplosionDescription()
    {
        super();
        stateTime = 0;
    }
    public ExplosionDescription setStateTime(float stateTime) {this.stateTime = stateTime; return this;}
    public float getStateTime() {return stateTime;}
}
