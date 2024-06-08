package com.pao.game.model.GameObject.Others.Tank;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class TankCreatingParams extends CreatingParamsRectangle {
    public TankCreatingParams()
    {
        setRealWidth(70);
        setRealHeight(60);
        setBodyType(BodyDef.BodyType.DynamicBody);
        setDensity(1f);
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Tank tank = new Tank(this,board);
        board.addObject(tank);
        return tank;
    }
}
