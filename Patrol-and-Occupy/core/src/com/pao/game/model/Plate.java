package com.pao.game.model;

public class Plate extends PolygonGameObject {
    ModelPlayer color;
    public Plate(float x, float y) {
        super(x, y, 80, 80);
        this.color = null;
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
