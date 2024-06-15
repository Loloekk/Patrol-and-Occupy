package com.pao.game.model.GameObject.Others.Tank;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.modelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class TankCreatingParams extends CreatingParamsRectangle {
    public TankCreatingParams()
    {
        setRealWidth(modelConstants.getConstant("tank.Width"));
        setRealHeight(modelConstants.getConstant("tank.Height"));
        setBodyType(BodyDef.BodyType.DynamicBody);
        setDensity(modelConstants.getConstant("tank.Density"));
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
