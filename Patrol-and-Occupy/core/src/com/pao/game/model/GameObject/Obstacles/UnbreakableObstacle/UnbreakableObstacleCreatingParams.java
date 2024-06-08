package com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class UnbreakableObstacleCreatingParams extends ObstacleCreatingParams {
    public UnbreakableObstacleCreatingParams() { super(); }
    @Override
    public BodyGameObject create(Board board)
    {
        UnbreakableObstacle unbreakableObstacle = new UnbreakableObstacle(this,board);
        board.addObject(unbreakableObstacle);
        return unbreakableObstacle;
    }
}
