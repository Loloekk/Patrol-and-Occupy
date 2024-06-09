package com.pao.game.model.GameObject.Explosions.BulletShoot;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BulletShootCreatingParams extends ExplosionCreatingParams {
    Tank tank;
    public BulletShootCreatingParams()
    {
        super();
        setRadius(10);
        setLiveTime(0.2f);
        setIsSensor(false);
    }
    public BulletShootCreatingParams setTank(Tank tank)
    {
        this.tank = tank;
        return this;
    }
    public Tank getTank()
    {
        return tank;
    }
    @Override
    public BodyGameObject create(Board board)
    {
        BulletShoot bulletShoot = new BulletShoot(this,board);
        board.addObject(bulletShoot);
        return bulletShoot;
    }
}
