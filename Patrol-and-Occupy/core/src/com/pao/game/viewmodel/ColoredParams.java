package com.pao.game.viewmodel;

import com.pao.game.model.MyColor;

public class ColoredParams extends Params{
    MyColor color;
    public ColoredParams(MyColor color, float widht, float height, float x, float y, float rotation){
        super(widht, height, x, y, rotation);
        this.color=color;
    }
    public MyColor getColor() {
        return color;
    }
}