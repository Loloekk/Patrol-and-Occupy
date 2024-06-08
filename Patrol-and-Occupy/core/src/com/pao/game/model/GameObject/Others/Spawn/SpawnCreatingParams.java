package com.pao.game.model.GameObject.Others.Spawn;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;
import com.pao.game.model.GameObject.Others.Tank.TankCreatingParams;

public class SpawnCreatingParams extends CreatingParamsRectangle {
    TankCreatingParams TCP;
    public SpawnCreatingParams(TankCreatingParams TCP)
    {
        setRealWidth(160);
        setRealHeight(160);
        setBodyType(BodyDef.BodyType.StaticBody);
        setDensity(1f);
        setIsSensor(false);
        this.TCP=TCP;
    }
    public TankCreatingParams getTankCreatingParams()
    {
        return TCP;
    }
    @Override
    public BodyGameObject create(Board board)
    {
        Spawn spawn = new Spawn(this,board);
        board.addObject(spawn);
        return spawn;
    }
}
