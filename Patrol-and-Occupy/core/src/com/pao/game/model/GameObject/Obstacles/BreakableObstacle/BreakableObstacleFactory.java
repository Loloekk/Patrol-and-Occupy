package com.pao.game.model.GameObject.Obstacles.BreakableObstacle;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleFactory;

public class BreakableObstacleFactory extends ObstacleFactory {
    @Override
    public BreakableObstacleCreatingParams getNew()
    {
        return new BreakableObstacleCreatingParams();
    }
}