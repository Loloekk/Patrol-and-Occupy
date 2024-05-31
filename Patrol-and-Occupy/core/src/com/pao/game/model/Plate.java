package com.pao.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Plate extends BodyGameObject {
    static final int width = 80;
    static final int height = 80;
    ModelPlayer color;
    public Plate(float x, float y, World world) {
        super(x, y, 0f, 0f, 0f, BodyDef.BodyType.StaticBody, world, 1f, true);
        body.setUserData(this);
        this.color = null;
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
    @Override
    public float getWidth() {
        return width;
    }
    @Override
    public float getHeight() {
        return height;
    }
}
