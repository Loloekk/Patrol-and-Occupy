package com.pao.game.model.GameObject.Others.Plate;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class PlateCreatingParams extends CreatingParamsRectangle {
    public PlateCreatingParams()
    {
        setRealWidth(Constants.getConstant("plate.Heart.Width"));
        setRealHeight(Constants.getConstant("plate.Heart.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(Constants.getConstant("plate.Density"));
        setIsSensor(true);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Plate plate = new Plate(this,board);
        board.addObject(plate);
        return plate;
    }
}
