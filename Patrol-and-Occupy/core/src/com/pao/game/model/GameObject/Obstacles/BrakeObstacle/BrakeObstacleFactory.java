package com.pao.game.model.GameObject.Obstacles.BrakeObstacle;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleFactory;

public class BrakeObstacleFactory extends ObstacleFactory {
    @Override
    public BrakeObstacleCreatingParams getNew()
    {
        return new BrakeObstacleCreatingParams();
    }
}