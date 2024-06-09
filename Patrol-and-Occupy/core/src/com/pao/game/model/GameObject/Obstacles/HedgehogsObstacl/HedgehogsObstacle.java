package com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl;

import com.pao.game.communication.Descriptions.ConcreteDescription.HedgehogsObstacleDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;

public class HedgehogsObstacle extends Obstacle {
    boolean isActive = true;
    public HedgehogsObstacle(HedgehogsObstacleCreatingParams HOCP, Board board) {
        super(HOCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public HedgehogsObstacleDescription getDescription()
    {
        return (HedgehogsObstacleDescription) (new HedgehogsObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
