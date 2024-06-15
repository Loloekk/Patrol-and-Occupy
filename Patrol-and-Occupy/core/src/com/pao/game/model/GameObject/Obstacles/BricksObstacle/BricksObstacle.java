package com.pao.game.model.GameObject.Obstacles.BricksObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.BricksObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;

import java.util.List;

public class BricksObstacle extends Obstacle {
    boolean isActive = true;
    public BricksObstacle(BricksObstacleCreatingParams BOCP, Board board) {
        super(BOCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
        if(!(killer instanceof ExplosionCircle)) return;
        isActive = false;
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((new BricksObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation()));
    }
}
