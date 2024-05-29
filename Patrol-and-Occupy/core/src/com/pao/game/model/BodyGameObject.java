package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BodyGameObject implements GameObject {
    Body body;
    float width;
    float height;
    public BodyGameObject(float x, float y, float width, float height, float degree, BodyDef.BodyType bodyType, World world, float density, boolean isSensor) {
        body = BodyCreator.createBodyRectangle(x, y, width, height, degree, bodyType, world, density, isSensor);
        this.width = width;
        this.height = height;
    }

    public boolean intersects(GameObject gameObject) {
        return Intersector.overlapConvexPolygons(PolygonBody.getPolygonFromBody(body), gameObject.getPolygon());
    }
    public float getX() {
        return getPolygon().getX();
    }
    public float getY() {
        return getPolygon().getY();
    }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getRotation() {
        return body.getAngle() * MathUtils.radiansToDegrees;
    }
    public float[] getVertices() {
        return getPolygon().getVertices();
    }
    public Polygon getPolygon() {
        return PolygonBody.getPolygonFromBody(body);
    }
    public Body getBody() { return body; }
}
