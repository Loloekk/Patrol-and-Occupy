package com.pao.game.model.GameObject.Others.Tank.Controler;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.pao.game.communication.Move;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Others.Tank.Magazine.Magazine;
import com.pao.game.model.GameObject.Others.Tank.Magazine.MagazineOneButton;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelSettings;

public class ControlerOneButton implements Controler{
    Tank tank;
    Board board;
    Magazine magazine;

    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    Move lastSide;
    public ControlerOneButton(Tank tank){
        this.tank = tank;
        this.board = tank.getBoard();
        magazine = new MagazineOneButton(tank);
        moveForwardState = false;
        moveLeftState = false;
        moveRightState = true;
        moveBackwardsState = false;
        lastSide = Move.Right;
    }

    @Override
    public void setMove(Move move, boolean state) {
        if(move != Move.Forward) {
            return;
        }
        if( !moveForwardState && state )
        {
            moveLeftState = false;
            moveRightState = false;
            magazine.setMove(Move.Shoot,true);
            moveForwardState = true;
        }
        else if( moveForwardState && !state ){
            moveForwardState = false;
            if(lastSide == Move.Left){
                lastSide = Move.Right;
                moveRightState = true;
            }
            else if(lastSide == Move.Right){
                lastSide = Move.Left;
                moveLeftState = true;
            }
        }
    }

    @Override
    public void update(float time) {
        magazine.update(time);
        if(!tank.getIsAlive()) {
            tank.getBody().setAngularVelocity(0f);
            tank.getBody().setLinearVelocity(new Vector2(0f,0f));
            return;
        }

        Vector2 vel = tank.getBody().getLinearVelocity();
        if(moveLeftState && !moveRightState) {                   //rotate left
            if(moveBackwardsState && !moveForwardState){
                tank.getBody().setAngularVelocity(-ModelSettings.getRotateSpeed());
            }
            else {
                tank.getBody().setAngularVelocity(ModelSettings.getRotateSpeed());
            }
        }
        else if(!moveLeftState && moveRightState) {              //rotate right
            if(moveBackwardsState && !moveForwardState){
                tank.getBody().setAngularVelocity(ModelSettings.getRotateSpeed());
            }
            else {
                tank.getBody().setAngularVelocity(-ModelSettings.getRotateSpeed());
            }
        }
        else {
            tank.getBody().setAngularVelocity(0.0f);
        }
        float rideForwardSpeed = ModelSettings.getTankSpeed();
        float rideBackwardsSpeed = rideForwardSpeed* Constants.getConstant("tank.BackSpeedByForwardSpeed");
        if(moveForwardState && !moveBackwardsState) {            //move forward
            vel.x = rideForwardSpeed * MathUtils.cos(tank.getBody().getAngle());
            vel.y = rideForwardSpeed * MathUtils.sin(tank.getBody().getAngle());
        }
        else if(!moveForwardState && moveBackwardsState) {       //move backwards
            vel.x = -rideBackwardsSpeed * MathUtils.cos(tank.getBody().getAngle());
            vel.y = -rideBackwardsSpeed * MathUtils.sin(tank.getBody().getAngle());
        }
        else {
            vel.x = 0;
            vel.y = 0;
        }
        tank.getBody().setLinearVelocity(vel);
    }

    @Override
    public Magazine getMagazine() {
        return magazine;
    }
}
