package com.pao.game.model.GameObject.Bodies;


import com.pao.game.communication.Descriptions.ObjectDescription;

public interface GameObject {
    float getX();
    float getY();
    float getRotation();
    void takeDamage(BodyGameObject obj);
    boolean getIsActive();
    float getWidth();
    float getHeight();
    ObjectDescription getDescription();
}
