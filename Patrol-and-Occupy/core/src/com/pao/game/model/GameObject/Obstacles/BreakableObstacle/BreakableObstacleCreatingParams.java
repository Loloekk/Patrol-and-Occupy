package com.pao.game.model.GameObject.Obstacles.BreakableObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class BreakableObstacleCreatingParams extends ObstacleCreatingParams {
    public BreakableObstacleCreatingParams() { super(); }
    @Override
    public BodyGameObject create(Board board)
    {
        BreakableObstacle breakableObstacle = new BreakableObstacle(this,board);
        board.addObject(breakableObstacle);
        return breakableObstacle;
    }
}
