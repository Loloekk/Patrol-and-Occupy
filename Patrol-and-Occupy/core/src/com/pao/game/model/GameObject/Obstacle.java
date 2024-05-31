package com.pao.game.model.GameObject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.Params;

import java.util.ArrayList;
import java.util.List;

public class Obstacle extends BodyGameObject {
    public Obstacle(float x, float y, float width, float height, float degree, World world) {
        super(x, y, width, height, degree, BodyDef.BodyType.StaticBody, world, 1f, false);
    }
    public static List<Params> rectangleObstacle(float x, float y, float side, int numberOfRows, int numberOfColumns, float degree) {
        List<Params> obstacleList = new ArrayList<>();
        float y1 = y - (numberOfRows - 1) / 2.0f * side;
        for(int i = 0; i < numberOfRows; i++) {
            float x1 = x - (numberOfColumns - 1) / 2.0f * side;
            for(int j = 0; j < numberOfColumns; j++) {
                Params obstacle = new Params(side, side, x + (x1-x) * MathUtils.cos(degree * MathUtils.degreesToRadians) - (y1-y) * MathUtils.sin(degree * MathUtils.degreesToRadians),
                                            y + (x1-x) * MathUtils.sin(degree * MathUtils.degreesToRadians) + (y1-y) * MathUtils.cos(degree * MathUtils.degreesToRadians), degree);
                obstacleList.add(obstacle);
                x1 += side;
            }
            y1 += side;
        }
        return obstacleList;
    }
}
