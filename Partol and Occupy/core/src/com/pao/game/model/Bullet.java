package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.pao.game.viewmodel.*;

public class Bullet extends GameObject{
    Board board;
    Color color;
    Bullet(Tank tank){
        super(tank.getX(), tank.getY(), 3, 5);
        polygon.setRotation(tank.getRotation());
        color = tank.getColor();
    }
    public void update(long t){
        final float speed = 0.1f;
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float dx = MathUtils.cos(angle) * speed;
        float dy = MathUtils.sin(angle) * speed;
        polygon.translate(dx, dy);
        if(board.checkTankCollision(this) || board.checkBoardCollision(this)) {
            polygon.translate(-dx, -dy);
        }
    }
    public void destroy(){
        if(!board.getBulletList().contains(this)){
            throw new RuntimeException("Bullet is not present in getBulletList()");
        }
        board.getBulletList().remove(this);
    }
    public Color getColor(){
        return color;
    }
}
