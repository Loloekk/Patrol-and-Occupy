package com.pao.game.model.GameObject.Others.Bullet;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class BulletCreatingParams extends CreatingParamsRectangle {
    public BulletCreatingParams()
    {
        setRealWidth(ModelConstants.getConstant("bullet.Width"));
        setRealHeight(ModelConstants.getConstant("bullet.Height"));
        setBodyType(BodyDef.BodyType.DynamicBody);
        setDensity(ModelConstants.getConstant("bullet.Density"));
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Bullet bullet = new Bullet(this,board);
        board.addObject(bullet);
        return bullet;
    }
}
