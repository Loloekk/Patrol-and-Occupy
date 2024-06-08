package com.pao.game.model.GameObject.Bodies;


public interface GameObject {
    float getX();
    float getY();
    float getRotation();
    void takeDamage(BodyGameObject obj);
    boolean getIsActive();
    float getWidth();
    float getHeight();
}
