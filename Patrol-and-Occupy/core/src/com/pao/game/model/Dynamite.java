package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.BodyDef;

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
        // Boom, destroys every tank in given range
        for (Tank tank : board.getTankList()) {
            double distance = Math.sqrt( (this.getX()-tank.getX()) * (this.getX()-tank.getX()) + (this.getY()-tank.getY()) * (this.getY()-tank.getY()) );
            if(distance <= 300){
                tank.setIsAlive(false);
            }
        }
        board.getDynamiteList().remove(this);
    }
}