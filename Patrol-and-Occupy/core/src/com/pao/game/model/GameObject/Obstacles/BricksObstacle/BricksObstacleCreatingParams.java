package com.pao.game.model.GameObject.Obstacles.BricksObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class BricksObstacleCreatingParams extends ObstacleCreatingParams {
    public BricksObstacleCreatingParams() { super(); }
    @Override
    public BodyGameObject create(Board board)
    {
        BricksObstacle bricksObstacle = new BricksObstacle(this,board);
        board.addObject(bricksObstacle);
        return bricksObstacle;
    }
}
