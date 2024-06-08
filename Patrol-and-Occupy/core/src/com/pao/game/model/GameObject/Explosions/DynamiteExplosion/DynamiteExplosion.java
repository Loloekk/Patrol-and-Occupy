package com.pao.game.model.GameObject.Explosions.DynamiteExplosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;

public class DynamiteExplosion extends Explosion {
    public DynamiteExplosion(DynamiteExplosionCreatingParams DECP, Board board) {
        super(DECP,board);    // animationTime
        body.setUserData(this);
    }
}
