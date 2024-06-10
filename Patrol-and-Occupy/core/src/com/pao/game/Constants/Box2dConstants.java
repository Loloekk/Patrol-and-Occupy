package com.pao.game.Constants;

import com.badlogic.gdx.math.Vector2;
import com.pao.game.Constants.Constants;

public class Box2dConstants {
    Box2dConstants(){}
    public static final Vector2 GRAVITY = new Vector2(0, 0);
    public static final float PPM = Constants.getConstant("PPM");
    public static final float VELOCITY_ITERATION = Constants.getConstant("VELOCITY_ITERATION");
    public static final float POSITION_ITERATION = Constants.getConstant("POSITION_ITERATION");
}
