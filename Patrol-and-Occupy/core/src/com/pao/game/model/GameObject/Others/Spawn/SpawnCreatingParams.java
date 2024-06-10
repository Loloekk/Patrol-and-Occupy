package com.pao.game.model.GameObject.Others.Spawn;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class SpawnCreatingParams extends CreatingParamsRectangle {
    public SpawnCreatingParams()
    {
        setRealWidth(Constants.getConstant("spawn.Width"));
        setRealHeight(Constants.getConstant("spawn.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(Constants.getConstant("spawn.Density"));
        setIsSensor(false);
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Spawn spawn = new Spawn(this,board);
        board.addObject(spawn);
        return spawn;
    }
}
