package com.pao.game.model;

import com.pao.game.viewmodel.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Tank extends GameObject{
    Color color;
    Board board;
    float rideForwardSpeed = 400;
    float rideBackwardsSpeed = 200;
    float rotateSpeed = 200;
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
        this.isAlive = true;
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
        if(!isAlive) return;

        int countKeyRide = 0;
        int countKeyRotate = 0;
        int countKey;
        if(moveForwardState) countKeyRide++;
        if(moveBackwardsState) countKeyRide++;
        countKeyRide %= 2;
        if(moveLeftState) countKeyRotate++;
        if(moveRightState) countKeyRotate++;
        countKeyRotate %= 2;
        countKey = countKeyRide + countKeyRotate;

        if(countKey == 0) {
            if(board.checkBulletCollision(this)) {
                isAlive = false;
            }
            return;
        }

        float timeDivided = time / countKey;    // /0
        float dX = 0;
        float dY = 0;
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float dAngle = 0;
        if(moveForwardState && !moveBackwardsState) {       //move forward
            dX = MathUtils.cos(angle) * rideForwardSpeed * timeDivided;
            dY = MathUtils.sin(angle) * rideForwardSpeed * timeDivided;
            polygon.translate(dX, dY);
        }
        if(!moveForwardState && moveBackwardsState) {       //move backwards
            dX = -MathUtils.cos(angle) * rideBackwardsSpeed * timeDivided;
            dY = -MathUtils.sin(angle) * rideBackwardsSpeed * timeDivided;
            polygon.translate(dX, dY);
        }
        if(moveLeftState && !moveRightState) {      //rotate left
            dAngle = rotateSpeed * timeDivided;
            polygon.rotate(dAngle);
        }
        if(!moveLeftState && moveRightState) {      //rotate right
            dAngle = -rotateSpeed * timeDivided;
            polygon.rotate(dAngle);
        }

        if(board.checkTankCollision(this) || board.checkBoardCollision(this)) {
            polygon.translate(-dX, -dY);
            polygon.rotate(-dAngle);
        }
        if(board.checkBulletCollision(this)) {
            isAlive = false;
        }

    }
    //    public boolean checkIfShooted() {
//        return board.checkBulletCollision(this);
//    }
    public void shoot() {
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float x = getX() + MathUtils.cos(angle) * getHeight()/2;
        float y = getY() + MathUtils.sin(angle) * getHeight()/2;
        board.addBullet(new Bullet(x, y, this));
    }
}