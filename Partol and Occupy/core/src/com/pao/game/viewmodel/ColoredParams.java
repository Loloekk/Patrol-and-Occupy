package com.pao.game.viewmodel;

public class ColoredParams extends Params{
    Color color;
    public ColoredParams(Color color, float widht, float height, float x, float y, float rotation){
        super(widht, height, x, y, rotation);
        this.color=color;
    }
    public Color getColor() {
        return color;
    }
}
