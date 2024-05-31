package com.pao.game.model.GameObject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.BodyCreator;
import com.pao.game.model.GameObject.GameObject;

import static com.pao.game.model.Constants.PPM;

public abstract class BodyGameObject implements GameObject {
    Body body;
    float width;
    float height;
    public BodyGameObject(float x, float y, float width, float height, float degree, BodyDef.BodyType bodyType, World world, float density, boolean isSensor) {
        body = BodyCreator.createBodyRectangle(x, y, width, height, degree, bodyType, world, density, isSensor);
        this.width = width;
        this.height = height;
    }
    public float getX() { return PPM * body.getPosition().x; }
    public float getY() {
        return PPM * body.getPosition().y;
    }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getRotation() {
        return body.getAngle() * MathUtils.radiansToDegrees;
    }
    public Body getBody() { return body; }
}
