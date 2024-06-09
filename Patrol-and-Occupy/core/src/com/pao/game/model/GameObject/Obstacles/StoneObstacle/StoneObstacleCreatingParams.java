package com.pao.game.model.GameObject.Obstacles.StoneObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class StoneObstacleCreatingParams extends ObstacleCreatingParams {
    public StoneObstacleCreatingParams() { super(); }
    @Override
    public BodyGameObject create(Board board)
    {
        StoneObstacle unbreakableObstacle = new StoneObstacle(this,board);
        board.addObject(unbreakableObstacle);
        return unbreakableObstacle;
    }
}
