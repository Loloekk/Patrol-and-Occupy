package com.pao.game.model.GameObject.Explosions.Explosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.ModelPlayer;

public abstract class ExplosionRectangle extends BodyGameObject {
    float width;
    float height;
    float x, y;
    float stateTime;
    float liveTime;
    boolean isActive;
    ModelPlayer color;
    public ExplosionRectangle(ExplosionRectangleCreatingParams ERCP, Board board) {
        super(ERCP,board.getWorld());
        this.x = ERCP.getX();
        this.y = ERCP.getY();
        this.width = 2*ERCP.getRealWidth();
        this.height = 2*ERCP.getRealHeight();
        this.liveTime = ERCP.getLiveTime();
        stateTime = 0;
        isActive = true;
        this.color = ERCP.getColor();
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
