package com.pao.game.model.GameObject.Obstacles.AbstractObstacle;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public abstract class ObstacleCreatingParams extends CreatingParamsRectangle {
    public ObstacleCreatingParams()
    {
        setRealWidth(Constants.getConstant("obstacle.Width"));
        setRealHeight(Constants.getConstant("obstacle.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(Constants.getConstant("obstacle.Density"));
        setIsSensor(false);
    }
}
