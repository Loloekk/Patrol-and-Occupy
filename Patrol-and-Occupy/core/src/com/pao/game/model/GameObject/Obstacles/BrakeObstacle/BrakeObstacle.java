package com.pao.game.model.GameObject.Obstacles.BrakeObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.BrakeObstacleDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.modelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BrakeObstacle extends Obstacle {
    float width = modelConstants.getConstant("obstacle.Width");
    float height = modelConstants.getConstant("obstacle.Height");
    boolean isActive = true;
    public BrakeObstacle(BrakeObstacleCreatingParams BOCP, Board board) {
        super(BOCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
        if(!(killer instanceof Tank)) return;
        isActive = false;
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public BrakeObstacleDescription getDescription()
    {
        return (BrakeObstacleDescription) (new BrakeObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
    @Override
    public float getWidth()
    {
        return width;
    }
    @Override
    public float getHeight()
    {
        return height;
    }
}
