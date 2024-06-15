package com.pao.game.model.GameObject.Explosions.Explosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.modelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public abstract class ExplosionRectangleCreatingParams extends CreatingParamsRectangle {
    float liveTime;
    public ExplosionRectangleCreatingParams()
    {
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(modelConstants.getConstant("explosion.Density"));
        setIsSensor(true);
    }
    public ExplosionRectangleCreatingParams setLiveTime(float time)
    {
        liveTime = time;
        return this;
    }
    public float getLiveTime()
    {
        return liveTime;
    }
    @Override
    public BodyGameObject create(Board board)
    {
        return null;
    }
}
