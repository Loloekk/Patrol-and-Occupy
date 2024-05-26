package com.pao.game.model;

public class Spawn extends PolygonGameObject{
    ModelPlayer color;
    public Spawn(float x, float y, ModelPlayer color) {
        super(x, y, 160, 160);
        this.color = color;
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
