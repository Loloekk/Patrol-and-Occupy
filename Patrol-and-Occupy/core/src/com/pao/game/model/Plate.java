package com.pao.game.model;

public class Plate extends PolygonGameObject {
    MyColor color;
    public Plate(float x, float y) {
        super(x, y, 80, 80);
        this.color = null;
    }
    public void setColor(MyColor color) { this.color = color; }
    public MyColor getColor() { return color; }
}
