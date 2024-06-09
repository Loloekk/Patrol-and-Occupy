package com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.UnbreakableObstacleDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;

public class UnbreakableObstacle extends Obstacle {
    boolean isActive = true;
    public UnbreakableObstacle(UnbreakableObstacleCreatingParams UBCP, Board board) {
        super(UBCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public UnbreakableObstacleDescription getDescription()
    {
        return (UnbreakableObstacleDescription) (new UnbreakableObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
