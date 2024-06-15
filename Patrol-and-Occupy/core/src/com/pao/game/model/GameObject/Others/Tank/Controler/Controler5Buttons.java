package com.pao.game.model.GameObject.Others.Tank.Controler;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.pao.game.communication.Move;
import com.pao.game.model.Boards.Board;
import com.pao.game.constants.ModelConstants;
import com.pao.game.model.GameObject.Others.Tank.Magazine.Magazine;
import com.pao.game.model.GameObject.Others.Tank.Magazine.Magazine5Buttons;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelSettings;

public class Controler5Buttons implements Controler{

    Tank tank;
    Board board;
    Magazine magazine;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    public Controler5Buttons(Tank tank){
        this.tank = tank;
        this.board = tank.getBoard();
        magazine = new Magazine5Buttons(tank);
        moveForwardState = false;
        moveLeftState = false;
        moveRightState = false;
        moveBackwardsState = false;
    }

    @Override
    public void setMove(Move move, boolean state) {
        switch (move) {
            case Forward:
                moveForwardState = state;
                break;
            case Back:
                moveBackwardsState = state;
                break;
            case Left:
                moveLeftState = state;
                break;
            case Right:
                moveRightState = state;
                break;
            default:
                magazine.setMove(move, state);
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
        float rideBackwardsSpeed = rideForwardSpeed* ModelConstants.getConstant("tank.BackSpeedByForwardSpeed");
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
