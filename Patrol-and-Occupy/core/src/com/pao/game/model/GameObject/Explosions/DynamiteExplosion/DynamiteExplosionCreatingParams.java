package com.pao.game.model.GameObject.Explosions.DynamiteExplosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCreatingParams;

public class DynamiteExplosionCreatingParams extends ExplosionCreatingParams {
    public DynamiteExplosionCreatingParams()
    {
        super();
        setRadius(150);
        setLiveTime(0.5f);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        DynamiteExplosion dynamiteExplosion = new DynamiteExplosion(this,board);
        board.addObject(dynamiteExplosion);
        return dynamiteExplosion;
    }
}
