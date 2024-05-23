package com.pao.game.model;

import com.badlogic.gdx.math.Polygon;

public interface GameObject {
    boolean intersects(GameObject gameObject);
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    float getRotation();
    float[] getVertices();
    Polygon getPolygon();
}
