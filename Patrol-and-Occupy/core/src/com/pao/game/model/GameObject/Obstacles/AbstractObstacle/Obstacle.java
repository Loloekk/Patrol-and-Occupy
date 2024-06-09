package com.pao.game.model.GameObject.Obstacles.AbstractObstacle;

import com.badlogic.gdx.math.MathUtils;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class Obstacle extends BodyGameObject {
    public Obstacle(CreatingParamsRectangle CPR, Board board) {
        super(CPR,board.getWorld());
    }
    public static List<ObstacleCreatingParams> rectangleObstacle(float x, float y, float side, int numberOfRows, int numberOfColumns, float degree,ObstacleFactory factory) {
        List<ObstacleCreatingParams> obstacleList = new ArrayList<>();
        float y1 = y - (numberOfRows - 1) / 2.0f * side;
        for(int i = 0; i < numberOfRows; i++) {
            float x1 = x - (numberOfColumns - 1) / 2.0f * side;
            for(int j = 0; j < numberOfColumns; j++) {
                ObstacleCreatingParams obstacle = (ObstacleCreatingParams)factory.getNew()
                        .setX(x + (x1-x) * MathUtils.cos(degree * MathUtils.degreesToRadians) - (y1-y) * MathUtils.sin(degree * MathUtils.degreesToRadians))
                        .setY(y + (x1-x) * MathUtils.sin(degree * MathUtils.degreesToRadians) + (y1-y) * MathUtils.cos(degree * MathUtils.degreesToRadians))
                        .setRotation(degree);
                obstacleList.add(obstacle);
                x1 += side;
            }
            y1 += side;
        }
        return obstacleList;
    }
    public void update(float time)
    {
        super.update(time);
    }
}
