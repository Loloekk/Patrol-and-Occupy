package com.pao.game.model.GameObject.Explosions.DynamiteExplosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircleCreatingParams;

public class DynamiteExplosionCreatingParams extends ExplosionCircleCreatingParams {
    public DynamiteExplosionCreatingParams()
    {
        super();
        setRadius(ModelConstants.getConstant("dynamite.Explosion.Size"));
        setLiveTime(ModelConstants.getConstant("dynamite.Explosion.LifeTime"));
    }
    @Override
    public BodyGameObject create(Board board)
    {
        DynamiteExplosion dynamiteExplosion = new DynamiteExplosion(this,board);
        board.addObject(dynamiteExplosion);
        return dynamiteExplosion;
    }
}
