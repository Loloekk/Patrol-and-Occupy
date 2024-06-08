package com.pao.game.model.GameObject.Others.Dynamite;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.DynamiteExplosion.DynamiteExplosionCreatingParams;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;

public class Dynamite extends BodyGameObject {
    static final int RANGE = 300;    // = explosion.width / 4
    Board board;
    boolean isActive = true;
    public Dynamite(DynamiteCreatingParams DCP, Board board) {
        super(DCP, board.getWorld());
        body.setUserData(this);
        this.board = board;
    }

    public void takeDamage(BodyGameObject killer) {
        if(!(killer instanceof Explosion)) return;
        if(!isActive) return;
        isActive = false;
        board.addObjectToCreate(new DynamiteExplosionCreatingParams().setX(getX()).setY(getY()).setColor(((Explosion)killer).getColor()));
    }
    public void update(float t){
        float vx = body.getLinearVelocity().x;
        float vy = body.getLinearVelocity().y;
        body.setLinearVelocity((float) (vx * Math.pow(0.01,t)), (float) (vy * Math.pow(0.01,t)));
        float va = body.getAngularVelocity();
        body.setAngularVelocity((float) (va * Math.pow(0.01,t)));
    }
    public boolean getIsActive() { return isActive; }
}