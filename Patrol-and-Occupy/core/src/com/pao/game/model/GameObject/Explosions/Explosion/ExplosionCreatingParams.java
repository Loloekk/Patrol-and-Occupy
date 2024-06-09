package com.pao.game.model.GameObject.Explosions.Explosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsCircle;

public class ExplosionCreatingParams extends CreatingParamsCircle {
    float liveTime;
    public ExplosionCreatingParams()
    {
        super();
        setRadius(20);
        setBodyType(BodyDef.BodyType.StaticBody);
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
