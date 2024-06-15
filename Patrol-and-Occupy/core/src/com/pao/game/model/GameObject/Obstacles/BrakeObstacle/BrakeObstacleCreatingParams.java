package com.pao.game.model.GameObject.Obstacles.BrakeObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.ModelConstants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.ObstacleCreatingParams;

public class BrakeObstacleCreatingParams extends ObstacleCreatingParams {
    public BrakeObstacleCreatingParams() {
        super();
        setRealHeight(ModelConstants.getConstant("brake.Obstacle.Width"));
        setRealWidth(ModelConstants.getConstant("brake.Obstacle.Height"));
    }
    @Override
    public BodyGameObject create(Board board)
    {
        BrakeObstacle breakableObstacle = new BrakeObstacle(this,board);
        board.addObject(breakableObstacle);
        return breakableObstacle;
    }
}
