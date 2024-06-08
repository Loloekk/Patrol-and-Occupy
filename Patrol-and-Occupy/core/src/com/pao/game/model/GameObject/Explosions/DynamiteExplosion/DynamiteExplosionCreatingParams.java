package com.pao.game.model.GameObject.Explosions.DynamiteExplosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCreatingParams;

public class DynamiteExplosionCreatingParams extends ExplosionCreatingParams {
    public DynamiteExplosionCreatingParams()
    {
        setRadius(150);
        setLiveTime(0.5f);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        DynamiteExplosion dynamiteExplosion = new DynamiteExplosion(this,board);
        board.addObject(dynamiteExplosion);
        return dynamiteExplosion;
    }
}
