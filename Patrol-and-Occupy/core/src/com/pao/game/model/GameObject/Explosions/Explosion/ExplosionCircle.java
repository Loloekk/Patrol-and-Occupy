package com.pao.game.model.GameObject.Explosions.Explosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.ModelPlayer;

public abstract class ExplosionCircle extends BodyGameObject {
    float width;
    float height;
    float x, y;
    float stateTime;
    float liveTime;
    boolean isActive;
    ModelPlayer color;
    public ExplosionCircle(ExplosionCircleCreatingParams ECCP, Board board) {
        super(ECCP,board.getWorld());
        this.x = ECCP.getX();
        this.y = ECCP.getY();
        this.width = 2*ECCP.getRadius();
        this.height = 2*ECCP.getRadius();
        this.liveTime = ECCP.getLiveTime();
        stateTime = 0;
        isActive = true;
        this.color = ECCP.getColor();
    }

    public void update(float time) {
        stateTime += time;
        if(stateTime >= liveTime) isActive = false;
    }
    public boolean getIsActive() { return isActive; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getRotation() { return body.getAngle(); }
    public float getStateTime() { return stateTime; }
    public float getLiveTime() { return liveTime; }
    public ModelPlayer getColor() {return color;}
}
