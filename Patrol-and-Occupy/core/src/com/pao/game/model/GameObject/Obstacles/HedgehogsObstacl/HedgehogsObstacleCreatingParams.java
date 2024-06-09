package com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class HedgehogsObstacleCreatingParams extends ObstacleCreatingParams {
    public HedgehogsObstacleCreatingParams() { super(); }
    @Override
    public BodyGameObject create(Board board)
    {
        HedgehogsObstacle hedgehogsObstacle = new HedgehogsObstacle(this,board);
        board.addObject(hedgehogsObstacle);
        return hedgehogsObstacle;
    }
}
