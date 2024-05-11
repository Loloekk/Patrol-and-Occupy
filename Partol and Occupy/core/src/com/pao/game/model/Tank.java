package com.pao.game.model;

public class Tank extends GameObject{
    Color color;
    Board board;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean isAlive;
    public Tank() {
        super();
    }
    void setMoveForwardState(boolean state) {
        moveForwardState = state;
    }
    void setMoveLeftState(boolean state) {
        moveLeftState = state;
    }
    void setMoveRightState(boolean state) {
        moveRightState = state;
    }
    void setMoveBackwardsState(boolean state) {
        moveBackwardsState = state;
    }
    void setIsAlive(boolean state) {
        isAlive = state;
    }
    void update(float time) {

    }
    void checkIfShooted() {

    }
    void shoot() {

    }
}
