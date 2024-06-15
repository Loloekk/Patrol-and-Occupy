package com.pao.game.model.GameObject.Obstacles.BrakeObstacle;

import com.pao.game.communication.Descriptions.ConcreteDescription.BrakeObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.DynamiteExplosion.DynamiteExplosion;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Others.Tank.Tank;

import java.util.List;

public class BrakeObstacle extends Obstacle {
    boolean isActive = true;
    public BrakeObstacle(BrakeObstacleCreatingParams BOCP, Board board) {
        super(BOCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
        if(!(killer instanceof Tank || killer instanceof DynamiteExplosion)) return;
        isActive = false;
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((new BrakeObstacleDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation()));
    }
}
