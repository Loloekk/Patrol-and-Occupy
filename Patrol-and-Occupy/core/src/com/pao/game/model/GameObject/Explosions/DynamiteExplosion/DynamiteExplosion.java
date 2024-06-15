package com.pao.game.model.GameObject.Explosions.DynamiteExplosion;

import com.pao.game.communication.Descriptions.ConcreteDescription.DynamiteExplosionDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;

import java.util.List;

public class DynamiteExplosion extends ExplosionCircle {
    public DynamiteExplosion(DynamiteExplosionCreatingParams DECP, Board board) {
        super(DECP,board);
        body.setUserData(this);
    }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((new DynamiteExplosionDescription())
                .setStateTime(getStateTime())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation()));
    }
}
