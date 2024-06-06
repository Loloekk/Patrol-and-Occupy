package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.DynamiteExplosion;
import com.pao.game.model.Explosion;
import com.pao.game.model.ModelPlayer;

import java.util.ArrayList;
import java.util.List;

public class Dynamite extends BodyGameObject {
    static final int RANGE = 75;    // = explosion.width / 4
    Board board;
    boolean isAlive = true;
    public Dynamite(float x, float y, Tank tank) {
        super(x, y, 40, 40, tank.getRotation(), BodyDef.BodyType.DynamicBody, tank.body.getWorld(), 0.7f, false);
        body.setUserData(this);
        this.board = tank.board;
    }

    public void takeDamage(ModelPlayer killer) {
        isAlive = false;
        board.addDynamiteExplosion(new DynamiteExplosion(getX(), getY()));
        double X = this.getX();
        double Y = this.getY();

         //Boom, destroys every Tank & Dynamite  in given range
        for (Tank tank : board.getTankList()) {
            double tX = tank.getX();
            double tY = tank.getY();
            double distance = Math.sqrt((X-tX) * (X-tX) + (Y-tY) * (Y-tY));
            double sX = tank.getSpawn().getX();
            double sY = tank.getSpawn().getY();
            double tankSpawnDistance = Math.sqrt((sX-tX) * (sX-tX) + (sY-tY) * (sY-tY));
            if (distance <= RANGE && tankSpawnDistance>60) {
                tank.takeDamage(killer);
            }
        }
        List<Dynamite> dynamites = new ArrayList<>(board.getDynamiteList());
        for (Dynamite dynamite : dynamites) {
            double dX = dynamite.getX();
            double dY = dynamite.getY();
            double distance = Math.sqrt((X-dX) * (X-dX) + (Y-dY) * (Y-dY));
            if (distance <= RANGE  &&  dynamite.getIsAlive())
                dynamite.takeDamage(killer);
        }
    }
    public void update(float t){
        float vx = body.getLinearVelocity().x;
        float vy = body.getLinearVelocity().y;
        body.setLinearVelocity((float) (vx * Math.pow(0.01,t)), (float) (vy * Math.pow(0.01,t)));
        float va = body.getAngularVelocity();
        body.setAngularVelocity((float) (va * Math.pow(0.01,t)));
    }
    public boolean getIsAlive() { return isAlive; }
}