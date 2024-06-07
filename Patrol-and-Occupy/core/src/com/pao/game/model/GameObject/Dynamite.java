package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.DynamiteExplosion;
import com.pao.game.model.ModelPlayer;

import java.util.ArrayList;
import java.util.List;

public class Dynamite extends BodyGameObject {
    static final int RANGE = 200;    // = explosion.width / 4
    Board board;
    ModelPlayer killer = null;
    boolean isActive = true;
    public Dynamite(float x, float y, Tank tank) {
        super(x, y, 40, 40, tank.getRotation(), BodyDef.BodyType.DynamicBody, tank.body.getWorld(), 0.7f, false);
        body.setUserData(this);
        this.board = tank.board;
    }

    public void takeDamage(BodyGameObject killer) {
        if(!(killer instanceof Bullet || (killer instanceof Dynamite && (!((Dynamite) killer).isActive)))) return;
        if(!isActive) return;
        if(killer instanceof Bullet){
            this.killer = ((Bullet) killer).getColor();
        }
        if(killer instanceof Dynamite){
            this.killer = ((Dynamite) killer).getColor();
        }
        isActive = false;
        board.addDynamiteExplosion(new DynamiteExplosion(getX(), getY()));
        double X = this.getX();
        double Y = this.getY();

         //Boom, destroys every Tank & Dynamite  in given range
        for (BodyGameObject obj : board.getBodyObjects()) {
            double dX = obj.getX();
            double dY = obj.getY();
            double distance = Math.sqrt((X-dX) * (X-dX) + (Y-dY) * (Y-dY));
            if (distance <= RANGE  &&  obj.getIsActive())
                obj.takeDamage(this);
        }
    }
    public void update(float t){
        float vx = body.getLinearVelocity().x;
        float vy = body.getLinearVelocity().y;
        body.setLinearVelocity((float) (vx * Math.pow(0.01,t)), (float) (vy * Math.pow(0.01,t)));
        float va = body.getAngularVelocity();
        body.setAngularVelocity((float) (va * Math.pow(0.01,t)));
    }
    public ModelPlayer getColor(){
        return killer;
    }
    public boolean getIsActive() { return isActive; }
}