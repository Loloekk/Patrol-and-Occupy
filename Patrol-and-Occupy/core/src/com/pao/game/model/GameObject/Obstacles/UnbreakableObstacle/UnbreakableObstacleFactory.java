package com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle;

import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.*;

public class UnbreakableObstacleFactory extends ObstacleFactory {
    @Override
    public UnbreakableObstacleCreatingParams getNew()
    {
        return new UnbreakableObstacleCreatingParams();
    }
}
