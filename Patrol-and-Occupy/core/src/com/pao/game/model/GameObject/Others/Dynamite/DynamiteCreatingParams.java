package com.pao.game.model.GameObject.Others.Dynamite;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class DynamiteCreatingParams extends CreatingParamsRectangle {
    public DynamiteCreatingParams()
    {
        setRealWidth(40);
        setRealHeight(40);
        setBodyType(BodyDef.BodyType.DynamicBody);
        setDensity(0.7f);
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
