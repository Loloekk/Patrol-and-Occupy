package com.pao.game.model.GameObject.Explosions.BulletShoot;

import com.badlogic.gdx.math.MathUtils;
import com.pao.game.communication.Descriptions.ConcreteDescription.BulletShootDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BulletShoot extends Explosion {
    Tank tank;
    public BulletShoot(BulletShootCreatingParams BSCP, Board board) {
        super(BSCP,board);
        this.tank = BSCP.getTank();
        body.setUserData(this);
    }

    @Override
    public float getX() { return tank.getX() + MathUtils.cos(tank.getRotation() * MathUtils.degreesToRadians) * tank.getWidth()/2; }
    @Override
    public float getY() { return tank.getY() + MathUtils.sin(tank.getRotation() * MathUtils.degreesToRadians) * tank.getWidth()/2; }
    @Override
    public float getRotation() { return tank.getRotation(); }
    @Override
    public BulletShootDescription getDescription()
    {
        return (BulletShootDescription) (new BulletShootDescription())
                .setStateTime(getStateTime())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
