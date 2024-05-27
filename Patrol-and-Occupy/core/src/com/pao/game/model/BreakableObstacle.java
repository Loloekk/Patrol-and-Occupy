package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.Communication.Params;

import java.util.ArrayList;
import java.util.List;

public class BreakableObstacle extends PolygonGameObject {
    public BreakableObstacle(float x, float y, float width, float height, float degree, World world) {
        super(x, y, width, height);
    }
    public static List<Params> rectangleObstacle(float x, float y, float side, int numberOfRows, int numberOfColumns, float degree) {
        List<Params> breakableObstacleList = new ArrayList<>();
        float y1 = y - (numberOfRows - 1) / 2.0f * side;
        for(int i = 0; i < numberOfRows; i++) {
            float x1 = x - (numberOfColumns - 1) / 2.0f * side;
            for(int j = 0; j < numberOfColumns; j++) {
                Params BreakableObstacle = new Params(side, side, x + (x1-x) * MathUtils.cos(degree * MathUtils.degreesToRadians) - (y1-y) * MathUtils.sin(degree * MathUtils.degreesToRadians),
                        y + (x1-x) * MathUtils.sin(degree * MathUtils.degreesToRadians) + (y1-y) * MathUtils.cos(degree * MathUtils.degreesToRadians), degree);
                breakableObstacleList.add(BreakableObstacle);
                x1 += side;
            }
            y1 += side;
        }
        return breakableObstacleList;
    }

    public void destroy(Board board){
        if (board.getBreakableObstacleList() == null)
            return;
        if (!board.getBreakableObstacleList().contains(this))
            return;
        double X = this.getX();
        double Y = this.getY();
        board.getBreakableObstacleList().remove(this);
    }
}
