package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.pao.game.viewmodel.*;

public class Bullet extends GameObject{
    Board board;
    Color color;
    Bullet(float x, float y, Tank tank){
        super(x, y, 10, 30);
        polygon.setRotation(tank.getRotation());
        color = tank.getColor();
        board = tank.board;
    }
    public void update(float t){
        final float speed = 550f;
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
    public Color getColor(){
        return color;
    }
}