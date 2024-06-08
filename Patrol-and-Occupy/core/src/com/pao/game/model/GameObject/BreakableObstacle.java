package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.Boards.Board;

public class BreakableObstacle extends Obstacle {
    boolean isActive = true;
    public BreakableObstacle(float x, float y, float width, float height, float degree, World world) {
        super(x, y, width, height, degree, world);
        body.setUserData(this);
    }
    public void takeDamage(BodyGameObject killer){
        if(!(killer instanceof Tank)) return;
        isActive = false;
    }
    public boolean getIsActive() { return isActive; }
}
