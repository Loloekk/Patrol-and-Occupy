package com.pao.game.model.GameObject.Obstacles.AbstractObstacle;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public abstract class ObstacleCreatingParams extends CreatingParamsRectangle {
    public ObstacleCreatingParams()
    {
        setRealWidth(20);
        setRealHeight(20);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
        setIsSensor(false);
    }
}
