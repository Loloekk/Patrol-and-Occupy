package com.pao.game.model.GameObject.Obstacles.BreakableObstacle;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BreakableObstacle extends Obstacle {
    boolean isActive = true;
    public BreakableObstacle(BreakableObstacleCreatingParams BCP, Board board) {
        super(BCP, board);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
        if(!(killer instanceof Tank)) return;
        isActive = false;
    }
    public boolean getIsActive() { return isActive; }
}
