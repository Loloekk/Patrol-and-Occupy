package com.pao.game.model;

import com.pao.game.viewmodel.MyColor;

public class Plate extends PolygonGameObject {
    MyColor color;
    public Plate(float x, float y) {
        super(x, y, 80, 80);
        this.color = null;
    }
    public void setColor(MyColor color) { this.color = color; }
    public MyColor getColor() { return color; }
}
