package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;
import java.util.List;

public class Dynamite extends BodyGameObject {
    Board board;

    public Dynamite(float x, float y, Tank tank) {
        super(x, y, 40, 40, tank.getRotation(), BodyDef.BodyType.DynamicBody, tank.body.getWorld(), 0.7f, false);
        this.board = tank.board;
    }

    public void destroy(ModelPlayer killer) {
        if (board.getDynamiteList() == null)
            return;
        if (!board.getDynamiteList().contains(this))
            return;
        double X = this.getX();
        double Y = this.getY();
        board.getDynamiteList().remove(this);
        this.body.getWorld().destroyBody(this.body);

        // Boom, destroys every Tank & Dynamite  in given range
        for (Tank tank : board.getTankList()) {
            double tX = tank.getX();
            double tY = tank.getY();
            double distance = Math.sqrt((X-tX) * (X-tX) + (Y-tY) * (Y-tY));
            double sX = tank.getSpawn().getX();
            double sY = tank.getSpawn().getY();
            double tankSpawnDistance = Math.sqrt((sX-tX) * (sX-tX) + (sY-tY) * (sY-tY));
            if (distance <= 300 && tankSpawnDistance>60) {
                tank.kill(killer);
            }
        }
        List<Dynamite> dynamites = new ArrayList<>(board.getDynamiteList());
        for (Dynamite dynamite : dynamites) {
            if(!board.getDynamiteList().contains(dynamite))
                continue;
            double dX = dynamite.getX();
            double dY = dynamite.getY();
            double distance = Math.sqrt((X-dX) * (X-dX) + (Y-dY) * (Y-dY));
            if (distance <= 300)
                dynamite.destroy(killer);
        }
    }

    public void update(float t){
        float vx = body.getLinearVelocity().x;
        float vy = body.getLinearVelocity().y;
        body.setLinearVelocity((float) (vx * Math.pow(0.01,t)), (float) (vy * Math.pow(0.01,t)));
        float va = body.getAngularVelocity();
        body.setAngularVelocity((float) (va * Math.pow(0.01,t)));
    }
}