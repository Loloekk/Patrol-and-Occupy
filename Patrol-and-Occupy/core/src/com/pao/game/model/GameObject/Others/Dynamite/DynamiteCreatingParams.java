package com.pao.game.model.GameObject.Others.Dynamite;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class DynamiteCreatingParams extends CreatingParamsRectangle {
    public DynamiteCreatingParams()
    {
        setRealWidth(Constants.getConstant("dynamite.Width"));
        setRealHeight(Constants.getConstant("dynamite.Height"));
        setBodyType(BodyDef.BodyType.DynamicBody);
        setDensity(Constants.getConstant("dynamite.Density"));
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Dynamite dynamite = new Dynamite(this,board);
        board.addObject(dynamite);
        return dynamite;
    }
}
