package com.pao.game.model.GameObject.Obstacles.BricksObstacle;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.*;

public class BricksObstacleFactory extends ObstacleFactory {
    @Override
    public BricksObstacleCreatingParams getNew()
    {
        return new BricksObstacleCreatingParams();
    }
}
