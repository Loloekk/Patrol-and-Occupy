package com.pao.game.model.GameObject.Bodies;


import com.pao.game.communication.Descriptions.ObjectDescription;
import java.util.List;

public interface GameObject {
    float getX();
    float getY();
    float getRotation();
    void takeDamage(BodyGameObject obj);
    boolean getIsActive();
    float getWidth();
    float getHeight();
    List<ObjectDescription> getDescription();
}
