package com.pao.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;

import static com.pao.game.model.Constants.PPM;

public class Tank extends BodyGameObject {
    static final int width = 70;
    static final int height = 60;
    public static int getSWidth(){return width;}
    public static int getSHeight(){return height;}
    ModelPlayer color;
    Board board;
    Magazine magazine;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean makeShoot;
    boolean placeDynamite;
    boolean isAlive;

    ModelSettings settings;

    public Tank(float x, float y, ModelPlayer color, Board board, World world, ModelSettings settings) {
        super(x, y, width, height, 0, BodyDef.BodyType.DynamicBody, world, 1f, false);
        body.setUserData(this);
        this.settings = settings;
        this.color = color;
        this.board = board;
        this.isAlive = true;
        this.magazine = new Magazine(settings);
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
    public void setPlaceDynamite(boolean state) { placeDynamite = state; }
    public void setIsAlive(boolean state) {
        isAlive = state;
    }
    public void setPosition(float x, float y) { body.setTransform(x/PPM,y/PPM, 0);}
    public ModelPlayer getColor() {
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

        if(makeShoot && magazine.shoot()) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() + MathUtils.cos(angle) * getHeight()/2;
            float y = getY() + MathUtils.sin(angle) * getHeight()/2;
            board.addBullet(new Bullet(x, y, this, settings));
        }

        if(placeDynamite) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() - MathUtils.cos(angle) * getHeight();
            float y = getY() - MathUtils.sin(angle) * getHeight();
            Dynamite dynamite = new Dynamite(x, y, this, settings);
//            if(!board.checkBoardCollision(dynamite) && !board.checkObstacleCollision(dynamite) && !board.checkDynamiteCollision(dynamite) && !board.checkTankCollision(dynamite) && !board.checkBoardCollision(dynamite))
                board.addDynamite(dynamite);
        }


        Vector2 vel = body.getLinearVelocity();
        if(moveLeftState && !moveRightState) {                   //rotate left
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(-settings.getRotateSpeed());
            }
            else {
                body.setAngularVelocity(settings.getRotateSpeed());
            }
        }
        else if(!moveLeftState && moveRightState) {              //rotate right
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(settings.getRotateSpeed());
            }
            else {
                body.setAngularVelocity(-settings.getRotateSpeed());
            }
        }
        else {
            body.setAngularVelocity(0.0f);
        }
        float rideForwardSpeed = settings.getTankSpeed();
        float rideBackwardsSpeed = rideForwardSpeed*3/4;
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