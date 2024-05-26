package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;
import java.util.List;

public class Dynamite extends BodyGameObject {
    Board board;
    ModelSettings settings;

    public Dynamite(float x, float y, Tank tank, ModelSettings settings) {
        super(x, y, 40, 40, 0, BodyDef.BodyType.DynamicBody, tank.body.getWorld(), 0.7f, false);
        this.settings = settings;
        this.board = tank.board;
    }

    public void destroy() {
        if (board.getDynamiteList() == null)
            return;
        if (!board.getDynamiteList().contains(this))
            throw new RuntimeException("Dynamite is not present in getDynamiteList()");
        board.getDynamiteList().remove(this);

        // Boom, destroys every Tank & Dynamite  in given range
        for (Tank tank : board.getTankList()) {
            double distance = Math.sqrt((this.getX() - tank.getX()) * (this.getX() - tank.getX()) + (this.getY() - tank.getY()) * (this.getY() - tank.getY()));
            if (distance <= 300)
                tank.setIsAlive(false);
        }
        List<Dynamite> dynamites = new ArrayList<>(board.getDynamiteList());
        for (Dynamite dynamite : dynamites) {
            if(!board.getDynamiteList().contains(dynamite))
                continue;
            double distance = Math.sqrt((this.getX() - dynamite.getX()) * (this.getX() - dynamite.getX()) + (this.getY() - dynamite.getY()) * (this.getY() - dynamite.getY()));
            if (distance <= 300)
                dynamite.destroy();
        }
    }
}