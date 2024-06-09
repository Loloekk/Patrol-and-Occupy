package com.pao.game.model.GameObject.Explosions.Explosion;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.ModelPlayer;

public abstract class Explosion extends BodyGameObject {
    float width;
    float height;
    float x, y;
    float stateTime;
    float liveTime;
    boolean isActive;
    ModelPlayer color;
    public Explosion(ExplosionCreatingParams ECP, Board board) {
        super(ECP,board.getWorld());
        this.x = ECP.getX();
        this.y = ECP.getY();
        this.width = 2*ECP.getRadius();
        this.height = 2*ECP.getRadius();
        this.liveTime = ECP.getLiveTime();
        stateTime = 0;
        isActive = true;
        this.color = ECP.getColor();
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
