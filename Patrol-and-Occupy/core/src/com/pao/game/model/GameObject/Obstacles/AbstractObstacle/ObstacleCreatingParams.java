package com.pao.game.model.GameObject.Obstacles.AbstractObstacle;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.Constants.modelConstants;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public abstract class ObstacleCreatingParams extends CreatingParamsRectangle {
    public ObstacleCreatingParams()
    {
        setRealWidth(modelConstants.getConstant("obstacle.Width"));
        setRealHeight(modelConstants.getConstant("obstacle.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(modelConstants.getConstant("obstacle.Density"));
        setIsSensor(false);
    }
}
