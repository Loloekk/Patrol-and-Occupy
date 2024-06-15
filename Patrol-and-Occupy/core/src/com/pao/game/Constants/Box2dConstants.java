package com.pao.game.Constants;

import com.badlogic.gdx.math.Vector2;

public class Box2dConstants {
    Box2dConstants(){}
    public static final Vector2 GRAVITY = new Vector2(0, 0);
    public static final float PPM = modelConstants.getConstant("PPM");
    public static final float VELOCITY_ITERATION = modelConstants.getConstant("VELOCITY_ITERATION");
    public static final float POSITION_ITERATION = modelConstants.getConstant("POSITION_ITERATION");
}
