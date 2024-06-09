package com.pao.game.model.GameObject.Obstacles.StoneObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.StoneObstacleDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;

public class StoneObstacle extends Obstacle {
    boolean isActive = true;
    public StoneObstacle(StoneObstacleCreatingParams SOCP, Board board) {
        super(SOCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){

        System.out.println(killer);
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public StoneObstacleDescription getDescription()
    {
        return (StoneObstacleDescription) (new StoneObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
