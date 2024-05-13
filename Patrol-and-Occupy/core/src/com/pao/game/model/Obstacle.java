package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Obstacle extends GameObject{
    public Obstacle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public static List<Obstacle> rectangleObstacle(float x, float y, float side, int numberOfRows, int numberOfColumns, float degrees) {
        List<Obstacle> obstacleList = new ArrayList<>();
        float y1 = y - (numberOfRows - 1) / 2.0f * side;
        for(int i = 0; i < numberOfRows; i++) {
            float x1 = x - (numberOfColumns - 1) / 2.0f * side;
            for(int j = 0; j < numberOfColumns; j++) {
                Obstacle obstacle = new Obstacle(x1, y1, side, side);
                obstacle.polygon.translate(x-x1, y-y1);
                obstacle.polygon.rotate(degrees);
                obstacle.polygon.translate((x1-x) * MathUtils.cos(degrees * MathUtils.degreesToRadians) - (y1-y) * MathUtils.sin(degrees * MathUtils.degreesToRadians), (x1-x) * MathUtils.sin(degrees * MathUtils.degreesToRadians) + (y1-y) * MathUtils.cos(degrees * MathUtils.degreesToRadians));
                obstacleList.add(obstacle);
                x1 += side;
            }
            y1 += side;
        }
        return obstacleList;
    }
}
