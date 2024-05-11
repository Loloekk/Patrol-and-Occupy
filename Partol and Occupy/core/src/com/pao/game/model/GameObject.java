package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    Vector2 position;
    Polygon polygon;

    public GameObject(float[] vertices) {
        this.position = new Vector2();      //
        this.polygon = new Polygon(vertices);
    }

    boolean intersects(GameObject gameObject) {
        return Intersector.overlapConvexPolygons(polygon, gameObject.polygon);
    }
    float getX() {
        return polygon.getX();
    }
    float getY() {
        return polygon.getY();
    }
    float getRotation() {
        return polygon.getRotation();
    }
}
