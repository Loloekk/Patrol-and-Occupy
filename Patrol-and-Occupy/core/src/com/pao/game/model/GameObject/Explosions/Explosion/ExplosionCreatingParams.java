package com.pao.game.model.GameObject.Explosions.Explosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsCircle;

public class ExplosionCreatingParams extends CreatingParamsCircle {
    float liveTime;
    public ExplosionCreatingParams()
    {
        setRadius(20);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
        setIsSensor(false);
    }
    public ExplosionCreatingParams setLiveTime(float time)
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
