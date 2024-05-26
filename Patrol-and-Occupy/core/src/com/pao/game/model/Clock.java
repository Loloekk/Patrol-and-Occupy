package com.pao.game.model;

import jdk.internal.icu.text.NormalizerBase;

public class Clock {
    float remainingTime;
    ModelSettings settings;
    public Clock(ModelSettings settings)
    {
        this.settings = settings;
        remainingTime = settings.getGameTime();
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
    }
}
