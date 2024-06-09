package com.pao.game.model.GameObject.Explosions.BulletExplosion;

import com.pao.game.communication.Descriptions.ConcreteDescription.BulletExplosionDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;

public class BulletExplosion extends Explosion {
    public BulletExplosion(BulletExplosionCreatingParams BXCP, Board board) {
        super(BXCP,board);
        body.setUserData(this);
    }
    @Override
    public BulletExplosionDescription getDescription()
    {
        return (BulletExplosionDescription) (new BulletExplosionDescription())
                .setStateTime(getStateTime())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
