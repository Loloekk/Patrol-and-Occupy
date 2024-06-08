package com.pao.game.model.GameObject.Others.Plate;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;
import com.pao.game.model.GameObject.Others.Plate.Plate;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;

public class PlateCreatingParams extends CreatingParamsRectangle {
    public PlateCreatingParams()
    {
        setRealWidth(0);
        setRealHeight(0);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
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
