package com.pao.game.model;

import jdk.internal.icu.text.NormalizerBase;

public class Clock {
    float remainingTime;
    public Clock()
    {
        remainingTime = ModelSettings.getGameTime();
    }
    public void setRemainingTime(float time) {
        remainingTime = time;
    }
    public void addRemainingTime(float time) {
        remainingTime += time;
    }
    public float getRemainingTime(){
        return remainingTime;
    }
    public void update(float time)
    {
        remainingTime -=time;
        if(remainingTime < 0f){
            remainingTime = 0f;
        }
    }
}
