package com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.StoneObstacleDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;

public class StoneObstacle extends Obstacle {
    boolean isActive = true;
    public StoneObstacle(StoneObstacleCreatingParams UBCP, Board board) {
        super(UBCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
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
