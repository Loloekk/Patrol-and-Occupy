package com.pao.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;
import com.pao.game.Communication.ColoredParams;

import static com.pao.game.model.Constants.PPM;

public class Tank extends BodyGameObject {
    static final int width = 70;
    static final int height = 60;
    public static int getSWidth(){return width;}
    public static int getSHeight(){return height;}
    ModelPlayer color;
    Board board;
    Magazine magazine;
    PlayerStatistics playerStatistics;
    float lastPlaceDynamite;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean makeShoot;
    boolean placeDynamite;
    boolean isAlive;

    public Tank(float x, float y, ModelPlayer color, Board board, World world) {
        super(x, y, width, height, 0, BodyDef.BodyType.DynamicBody, world, 1f, false);
        body.setUserData(this);
        this.color = color;
        this.board = board;
        this.isAlive = true;
        this.magazine = new Magazine();
        this.lastPlaceDynamite = 0f;
        this.playerStatistics = new PlayerStatistics();
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
    public void revive() {
        isAlive = true;
    }
    public void kill(ModelPlayer killer)
    {
        if(isAlive == true)
        {
            playerStatistics.incrementDeadNumber();
            board.getTank(killer).getStatistics().incrementKillNumber();
            isAlive = false;
        }
    }
    public void setPosition(float x, float y) { body.setTransform(x/PPM,y/PPM, 0);}
    public ModelPlayer getColor() {
        return color;
    }
    public PlayerStatistics getStatistics()
    {
        return playerStatistics;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public void update(float time) {
        lastPlaceDynamite += time;
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
            board.addBullet(new Bullet(x, y, this));
        }

        if(placeDynamite && lastPlaceDynamite >= 10.0f) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() - MathUtils.cos(angle) * getHeight() * 1.1f;
            float y = getY() - MathUtils.sin(angle) * getHeight() * 1.1f;
            Dynamite dynamite = new Dynamite(x, y, this);
            board.addDynamite(dynamite);
            if(board.checkBoardCollision(dynamite) || board.checkObstacleCollision(dynamite) || board.checkDynamiteCollision(dynamite) || board.checkSpawnCollision(dynamite) || board.checkTankCollision(dynamite) || board.checkBreakableObstacleCollision(dynamite)){
                board.getDynamiteList().remove(dynamite);
                dynamite.body.getWorld().destroyBody(dynamite.body);
            }else{
                lastPlaceDynamite = 0;
            }
        }


        Vector2 vel = body.getLinearVelocity();
        if(moveLeftState && !moveRightState) {                   //rotate left
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(-ModelSettings.getRotateSpeed());
            }
            else {
                body.setAngularVelocity(ModelSettings.getRotateSpeed());
            }
        }
        else if(!moveLeftState && moveRightState) {              //rotate right
            if(moveBackwardsState && !moveForwardState){
                body.setAngularVelocity(ModelSettings.getRotateSpeed());
            }
            else {
                body.setAngularVelocity(-ModelSettings.getRotateSpeed());
            }
        }
        else {
            body.setAngularVelocity(0.0f);
        }
        float rideForwardSpeed = ModelSettings.getTankSpeed();
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
    }
    public Spawn getSpawn(){
        for(Spawn spawn : board.getSpawnList())
            if(this.color == spawn.color)
                return spawn;
        throw new RuntimeException("Spawn not found");
    }
}