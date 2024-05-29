package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Spawn extends BodyGameObject{
    static final int width = 160;
    static final int height = 160;
    ModelPlayer color;
    public Spawn(float x, float y, ModelPlayer color, World world) {
        super(x, y, width, height, 0, BodyDef.BodyType.StaticBody, world, 1f, false);
        body.setUserData(this);
        this.color = color;
    }

    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
