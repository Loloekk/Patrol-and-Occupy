package com.pao.game.model.GameObject.Others.Spawn;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

public class SpawnCreatingParams extends CreatingParamsRectangle {
    public SpawnCreatingParams()
    {
        setRealWidth(ModelConstants.getConstant("spawn.Width"));
        setRealHeight(ModelConstants.getConstant("spawn.Height"));
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(ModelConstants.getConstant("spawn.Density"));
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
