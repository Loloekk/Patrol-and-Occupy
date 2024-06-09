package com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.*;

public class StoneObstacleFactory extends ObstacleFactory {
    @Override
    public StoneObstacleCreatingParams getNew()
    {
        return new StoneObstacleCreatingParams();
    }
}
