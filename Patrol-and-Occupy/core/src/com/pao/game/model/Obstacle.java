package com.pao.game.model;

import java.util.ArrayList;
import java.util.List;

public class Obstacle extends GameObject{
    public Obstacle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public static List<Obstacle> horizontalObstacle(float x, float y, float side, int number) {
        List<Obstacle> obstacleList = new ArrayList<>();
        float x1 = x - (number - 1) / 2.0f * side;
        for(int i = 0; i < number; i++) {
            obstacleList.add(new Obstacle(x1, y, side, side));
            x1 += side;
        }
        return obstacleList;
    }
    public static List<Obstacle> verticalObstacle(float x, float y, float side, int number) {
        List<Obstacle> obstacleList = new ArrayList<>();
        float y1 = y - (number - 1) / 2.0f * side;
        for(int i = 0; i < number; i++) {
            obstacleList.add(new Obstacle(x, y1, side, side));
            y1 += side;
        }
        return obstacleList;
    }
}
