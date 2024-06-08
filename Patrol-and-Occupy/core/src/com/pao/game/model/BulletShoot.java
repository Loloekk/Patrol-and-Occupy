package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.pao.game.model.GameObject.Tank;

public class BulletShoot extends Explosion {
    static final float width = 50;
    static final float height = 37;
    Tank tank;
    public BulletShoot(float x, float y, Tank tank) {
        super(x, y, width, height, tank.getRotation(), 0.2f);     // animationTime
        this.tank = tank;
    }

    @Override
    public float getX() { return tank.getX() + MathUtils.cos(tank.getRotation() * MathUtils.degreesToRadians) * tank.getWidth()/2; }
    @Override
    public float getY() { return tank.getY() + MathUtils.sin(tank.getRotation() * MathUtils.degreesToRadians) * tank.getWidth()/2; }
    @Override
    public float getRotation() { return tank.getRotation(); }
}
