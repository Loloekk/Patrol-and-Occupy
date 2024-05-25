package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;

public class Bullet extends PolygonGameObject {
    Board board;
    ModelPlayer color;
    ModelSettings settings;
    Bullet(float x, float y, Tank tank,ModelSettings settings){
        super(x, y, 30, 10);
        polygon.setRotation(tank.getRotation());
        color = tank.getColor();
        board = tank.board;
        this.settings = settings;
    }
    public void update(float t){
        final float speed = settings.getBulletSpeed();
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float dx = MathUtils.cos(angle) * speed * t;
        float dy = MathUtils.sin(angle) * speed * t;
        polygon.translate(dx, dy);
    }
    public void destroy(){
        if(board.getBulletList() == null)
            return;
        if(!board.getBulletList().contains(this))
            throw new RuntimeException("Bullet is not present in getBulletList()");
        board.getBulletList().remove(this);
    }
    public ModelPlayer getColor(){
        return color;
    }
}