package com.pao.game.model;

import com.pao.game.model.ModelSettings;

public class Clock {
    float remainingTime;
    public Clock()
    {
        remainingTime = ModelSettings.getGameTime();
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
