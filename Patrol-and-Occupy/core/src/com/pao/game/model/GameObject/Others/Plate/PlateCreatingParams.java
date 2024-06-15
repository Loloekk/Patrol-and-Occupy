package com.pao.game.model.GameObject.Others.Plate;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.modelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class PlateCreatingParams extends CreatingParamsRectangle {
    public PlateCreatingParams()
    {
        setRealWidth(modelConstants.getConstant("plate.Heart.Width"));
        setRealHeight(modelConstants.getConstant("plate.Heart.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(modelConstants.getConstant("plate.Density"));
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
