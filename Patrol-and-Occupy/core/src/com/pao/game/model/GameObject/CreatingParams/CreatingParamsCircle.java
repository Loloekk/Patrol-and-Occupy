package com.pao.game.model.GameObject.CreatingParams;

public class CreatingParamsCircle extends CreatingParams {
    float radius;
    public CreatingParamsCircle() {
        radius = 0;
    }
    public CreatingParamsCircle setRadius(float radius)
    {
        this.radius = radius;
        return this;
    }
    public float getRadius(){
        return radius;
    }
}
