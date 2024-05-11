package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;

public class Tank extends GameObject{
    Color color;
    Board board;
    float rideSpeed = 10;
    float rotateSpeed = 5;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean isAlive;
    //final float width = 20;
    //final float height = 30;

    public Tank(float x, float y, Color color, Board board) {
        super(x, y, 20, 30);
        this.color = color;
        this.board = board;
    }

    public void setMoveForwardState(boolean state) {
        moveForwardState = state;
    }
    public void setMoveLeftState(boolean state) {
        moveLeftState = state;
    }
    public void setMoveRightState(boolean state) {
        moveRightState = state;
    }
    public void setMoveBackwardsState(boolean state) {
        moveBackwardsState = state;
    }
    public void setIsAlive(boolean state) {
        isAlive = state;
    }
      public Color getColor() {
        return color;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public void update(float time) {
        // booleany imply
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float dx = MathUtils.cos(angle) * speed;
        float dy = MathUtils.sin(angle) * speed;
        polygon.translate(dx, dy);
        if(board.checkTankCollision(this) || board.checkBoardCollision(this)) {
            polygon.translate(-dx, -dy);
        }
    }
    public void checkIfShooted() {

    }
    public void shoot() {
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float x = getX() + MathUtils.cos(angle) * getHeight()/2;
        float y = getY() + MathUtils.sin(angle) * getHeight()/2;
        board.addBullet(new Bullet(x, y, this));
    }
}
