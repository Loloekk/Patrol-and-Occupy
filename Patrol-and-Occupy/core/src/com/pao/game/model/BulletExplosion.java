package com.pao.game.model;

public class BulletExplosion extends Explosion {
    static final float width = 50;
    static final float height = 50;
    public BulletExplosion(float x, float y, float rotation) {
        super(x, y, width, height, rotation, 0.75f);    // animationTime
    }
}
