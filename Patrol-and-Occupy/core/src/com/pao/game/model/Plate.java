package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;

public class Plate extends PolygonGameObject {
    ModelPlayer color;
    PolygonGameObject heart;
    public Plate(float x, float y) {
        super(x, y, 80, 80);
        heart = new PolygonGameObject(x,y,10,10){};
        this.color = null;
    }
    @Override
    public boolean intersects(GameObject gameObject) {
        return heart.intersects(gameObject);
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
