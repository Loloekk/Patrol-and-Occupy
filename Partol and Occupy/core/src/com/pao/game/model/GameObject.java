package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    Polygon polygon;
    public GameObject(float x, float y, float width, float height) {
        float[] vertices = {x-width/2, y-height/2, x+width/2, y-height/2, x+width/2, y+height/2, x-width/2, y+height/2};
        polygon = new Polygon(vertices);
        polygon.setPosition(x, y);
        polygon.setOrigin(x, y);
    }

    public boolean intersects(GameObject gameObject) {
        return Intersector.overlapConvexPolygons(polygon, gameObject.polygon);
    }
    public float getX() {
        return polygon.getX();
    }
    public float getY() {
        return polygon.getY();
    }
    public float getWidth() {
        return ;
    }
    public float getHeight() {
        return ;
    }
    public float getRotation() {
        return polygon.getRotation();
    }
}
