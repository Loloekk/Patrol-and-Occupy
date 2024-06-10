package com.pao.game.model.GameObject.Explosions.BulletShoot;

import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionRectangleCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BulletShootCreatingParams extends ExplosionRectangleCreatingParams {
    Tank tank;
    public BulletShootCreatingParams()
    {
        super();
        setRealWidth(Constants.getConstant("bullet.Shoot.Size"));
        setRealHeight(Constants.getConstant("bullet.Shoot.Size"));
        setLiveTime(Constants.getConstant("bullet.Shoot.LifeTime"));
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
