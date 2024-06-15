package com.pao.game.model.GameObject.Explosions.BulletExplosion;

import com.pao.game.communication.Descriptions.ConcreteDescription.BulletExplosionDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;

import java.util.List;

public class BulletExplosion extends ExplosionCircle {
    public BulletExplosion(BulletExplosionCreatingParams BXCP, Board board) {
        super(BXCP,board);
        body.setUserData(this);
    }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((new BulletExplosionDescription())
                .setStateTime(getStateTime())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation()));
    }
}
