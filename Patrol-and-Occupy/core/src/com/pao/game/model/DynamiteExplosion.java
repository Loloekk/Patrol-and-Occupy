package com.pao.game.model;

public class DynamiteExplosion extends Explosion{
    static final float width = 300;
    static final float height = 300;
    public DynamiteExplosion(float x, float y) {
        super(x, y, width, height, 0f, 0.9f);    // animationTime
    }
}
