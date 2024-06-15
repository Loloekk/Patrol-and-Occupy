package com.pao.game.model.GameObject.Obstacles.AbstractObstacle;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.Constants.ModelConstants;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public abstract class ObstacleCreatingParams extends CreatingParamsRectangle {
    public ObstacleCreatingParams()
    {
        setRealWidth(ModelConstants.getConstant("obstacle.Width"));
        setRealHeight(ModelConstants.getConstant("obstacle.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(ModelConstants.getConstant("obstacle.Density"));
        setIsSensor(false);
    }
}
