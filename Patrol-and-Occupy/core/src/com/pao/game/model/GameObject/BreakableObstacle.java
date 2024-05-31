package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.Boards.Board;


public class BreakableObstacle extends Obstacle {
    public BreakableObstacle(float x, float y, float width, float height, float degree, World world) {
        super(x, y, width, height, degree, world);
        body.setUserData(this);
    }
    public void destroy(Board board){
        if (board.getBreakableObstacleList() == null)
            return;
        if (!board.getBreakableObstacleList().contains(this))
            return;
        double X = this.getX();
        double Y = this.getY();
        board.getBreakableObstacleList().remove(this);
        this.body.getWorld().destroyBody(this.body);
    }
}
