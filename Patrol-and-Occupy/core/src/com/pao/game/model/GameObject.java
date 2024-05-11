package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    Polygon polygon;
    float width;
    float height;
    public GameObject(float x, float y, float width, float height) {
        float[] vertices = {-height/2, -width/2, height/2, -width/2, height/2, width/2, -height/2, width/2};
        polygon = new Polygon(vertices);
        //polygon.setOrigin(x, y);
        polygon.setPosition(x, y);
        this.width = width;
        this.height = height;
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
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getRotation() {
        return polygon.getRotation();
    }
}