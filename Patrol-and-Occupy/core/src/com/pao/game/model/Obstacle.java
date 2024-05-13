package com.pao.game.model;

import java.util.ArrayList;
import java.util.List;

public class Obstacle extends GameObject{
    public Obstacle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public static List<Obstacle> rectangleObstacle(float x, float y, float side, int numberOfRows, int numberOfColumns) {
        List<Obstacle> obstacleList = new ArrayList<>();
        float y1 = y - (numberOfRows - 1) / 2.0f * side;
        for(int i = 0; i < numberOfRows; i++) {
            float x1 = x - (numberOfColumns - 1) / 2.0f * side;
            for(int j = 0; j < numberOfColumns; j++) {
                obstacleList.add(new Obstacle(x1, y1, side, side));
                x1 += side;
            }
            y1 += side;
        }
        return obstacleList;
    }
}
