package com.pao.game.model.GameObject.Explosions.BulletExplosion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCreatingParams;

public class BulletExplosionCreatingParams extends ExplosionCreatingParams {
    public BulletExplosionCreatingParams()
    {
        setRadius(20);
        setLiveTime(0.5f);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        BulletExplosion bulletExplosion = new BulletExplosion(this,board);
        board.addObject(bulletExplosion);
        return bulletExplosion;
    }
}
