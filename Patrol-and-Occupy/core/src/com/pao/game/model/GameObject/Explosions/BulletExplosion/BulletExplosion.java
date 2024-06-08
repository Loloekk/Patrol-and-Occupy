package com.pao.game.model.GameObject.Explosions.BulletExplosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;

public class BulletExplosion extends Explosion {
    static final float radius = 42;
    public BulletExplosion(BulletExplosionCreatingParams BXCP, Board board) {
        super(BXCP,board);    // animationTime
        body.setUserData(this);
    }
}
