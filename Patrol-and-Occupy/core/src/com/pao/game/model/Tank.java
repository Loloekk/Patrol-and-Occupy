package com.pao.game.model;

import com.pao.game.viewmodel.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Tank extends GameObject{
    Color color;
    Board board;
    float rideForwardSpeed = 300;
    float rideBackwardsSpeed = 150;
    float rotateSpeed = 200;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean isAlive;

    public Tank(float x, float y, Color color, Board board) {
        super(x, y, 60, 90);
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

        if(countKey == 2) time = time * 3 / 4;

        float dX = 0;
        float dY = 0;
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float dAngle = 0;
        int sign = 1;
        if(moveForwardState && !moveBackwardsState) {       //move forward
            dX = MathUtils.cos(angle) * rideForwardSpeed * time;
            dY = MathUtils.sin(angle) * rideForwardSpeed * time;
            polygon.translate(dX, dY);
        }
        if(!moveForwardState && moveBackwardsState) {       //move backwards
            sign = -1;
            dX = -MathUtils.cos(angle) * rideBackwardsSpeed * time;
            dY = -MathUtils.sin(angle) * rideBackwardsSpeed * time;
            polygon.translate(dX, dY);
        }
        if(moveLeftState && !moveRightState) {      //rotate left
            dAngle = sign * rotateSpeed * time;
            polygon.rotate(dAngle);
        }
        if(!moveLeftState && moveRightState) {      //rotate right
            dAngle = -sign * rotateSpeed * time;
            polygon.rotate(dAngle);
        }

        if(board.checkTankCollision(this) || board.checkBoardCollision(this) || board.checkObstacleCollision(this)) {
            polygon.translate(-dX, -dY);
            polygon.rotate(-dAngle);
        }
        if(board.checkBulletCollision(this)) {
            isAlive = false;
        }

    }
    public void shoot() {
        if(!isAlive) return;
        float angle = polygon.getRotation() * MathUtils.degreesToRadians;
        float x = getX() + MathUtils.cos(angle) * getHeight()/2;
        float y = getY() + MathUtils.sin(angle) * getHeight()/2;
        board.addBullet(new Bullet(x, y, this));
    }
}