package com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.*;

public class HedgehogsObstacleFactory extends ObstacleFactory {
    @Override
    public HedgehogsObstacleCreatingParams getNew()
    {
        return new HedgehogsObstacleCreatingParams();
    }
}
