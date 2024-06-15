package com.pao.game.model.GameObject.Others.Plate;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class PlateCreatingParams extends CreatingParamsRectangle {
    public PlateCreatingParams()
    {
        setRealWidth(ModelConstants.getConstant("plate.Heart.Width"));
        setRealHeight(ModelConstants.getConstant("plate.Heart.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(ModelConstants.getConstant("plate.Density"));
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
