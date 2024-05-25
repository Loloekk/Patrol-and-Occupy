package com.pao.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;

public class Tank extends BodyGameObject {
    MyColor color;
    Board board;
    Magazine magazine;
    float rideForwardSpeed;
    float rideBackwardsSpeed = 6f;
    float rotateSpeed = 4f;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean makeShoot;
    boolean isAlive;
    float lastShoot;

    ModelSettings settings;

    public Tank(float x, float y, MyColor color, Board board, World world, ModelSettings settings) {
        super(x, y, 70, 60, 0, BodyDef.BodyType.DynamicBody, world, 1f, false);
        this.settings = settings;
        this.color = color;
        this.board = board;
        this.isAlive = true;
        this.magazine = new Magazine(settings);
        rideForwardSpeed = settings.getTankSpeed();
        rideBackwardsSpeed = settings.getTankSpeed()/4*3;
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
    public void setMakeShoot(boolean state) {
        makeShoot = state;
    }
    public void setIsAlive(boolean state) {
        isAlive = state;
    }
    public MyColor getColor() {
        return color;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public void update(float time) {
        if(!isAlive) {
            body.setAngularVelocity(0f);
            body.setLinearVelocity(new Vector2(0f,0f));
            return;
        }
        magazine.update(time);

        if(makeShoot&& magazine.shoot())
        {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() + MathUtils.cos(angle) * getHeight()/2;
            float y = getY() + MathUtils.sin(angle) * getHeight()/2;
            board.addBullet(new Bullet(x, y, this,settings));
        }

        Vector2 vel = body.getLinearVelocity();
        if(moveLeftState && !moveRightState) {                   //rotate left
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(-rotateSpeed);
            }
            else {
                body.setAngularVelocity(rotateSpeed);
            }
        }
        else if(!moveLeftState && moveRightState) {              //rotate right
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(rotateSpeed);
            }
            else {
                body.setAngularVelocity(-rotateSpeed);
            }
        }
        else {
            body.setAngularVelocity(0.0f);
        }

        if(moveForwardState && !moveBackwardsState) {            //move forward
            vel.x = rideForwardSpeed * MathUtils.cos(body.getAngle());
            vel.y = rideForwardSpeed * MathUtils.sin(body.getAngle());
        }
        else if(!moveForwardState && moveBackwardsState) {       //move backwards
            vel.x = -rideBackwardsSpeed * MathUtils.cos(body.getAngle());
            vel.y = -rideBackwardsSpeed * MathUtils.sin(body.getAngle());
        }
        else {
            vel.x = 0;
            vel.y = 0;
        }
        body.setLinearVelocity(vel);

        if(board.checkBulletCollision(this)) {
            isAlive = false;
        }
    }
}