package com.pao.game.model.GameObject.Explosions.BulletExplosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircleCreatingParams;

public class BulletExplosionCreatingParams extends ExplosionCircleCreatingParams {
    public BulletExplosionCreatingParams()
    {
        super();
        setRadius(Constants.getConstant("bullet.Explosion.Size"));
        setLiveTime(Constants.getConstant("bullet.Explosion.LifeTime"));
    }
    @Override
    public BodyGameObject create(Board board)
    {
        BulletExplosion bulletExplosion = new BulletExplosion(this,board);
        board.addObject(bulletExplosion);
        return bulletExplosion;
    }
}
