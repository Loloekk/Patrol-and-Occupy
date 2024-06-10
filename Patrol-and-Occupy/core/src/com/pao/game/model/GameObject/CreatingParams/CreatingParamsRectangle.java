package com.pao.game.model.GameObject.CreatingParams;

public class CreatingParamsRectangle extends CreatingParams {
    float realWidth;
    float realHeight;
    public CreatingParamsRectangle()
    {
        realWidth = 0;
        realHeight = 0;
    }
    public CreatingParamsRectangle setRealWidth(float width)
    {
        this.realWidth =width;
        return this;
    }
    public CreatingParamsRectangle setRealHeight(float height)
    {
        this.realHeight =height;
        return this;
    }
    public float getRealWidth() {
        return realWidth;
    }
    public float getRealHeight() {
        return realHeight;
    }
}
