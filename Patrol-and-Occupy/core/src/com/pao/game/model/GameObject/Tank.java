package com.pao.game.model.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;

import static com.pao.game.model.Constants.PPM;

public class Tank extends BodyGameObject {
    static final int width = 70;
    static final int height = 60;
    ModelPlayer color;
    Board board;
    World world;
    Magazine magazine;
    PlayerStatistics playerStatistics;
    Spawn spawn;
    boolean moveForwardState;
    boolean moveLeftState;
    boolean moveRightState;
    boolean moveBackwardsState;
    boolean makeShoot;
    boolean placeDynamite;
    boolean isAlive;

    public Tank(float x, float y, ModelPlayer color, Board board) {
        super(x, y, width, height, 0, BodyDef.BodyType.DynamicBody, board.getWorld(), 1f, false);
        body.setUserData(this);
        this.color = color;
        this.board = board;
        this.world = board.getWorld();
        this.isAlive = true;
        this.magazine = new Magazine();
        this.playerStatistics = new PlayerStatistics();
        this.spawn = new Spawn(x,y,this);
        board.addObject(spawn);
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
    public void takeDamage(BodyGameObject killer)
    {
        if(!isAlive) return;
        double sX = spawn.getX();
        double sY = spawn.getY();
        double tankSpawnDistance = Math.sqrt((sX-this.getX()) * (sX-this.getX()) + (sY-this.getY()) * (sY-this.getY()));
        if(tankSpawnDistance <= 60) return;

        if(!(killer instanceof Bullet || (killer instanceof Dynamite && (!((Dynamite) killer).getIsActive())))) return;
        ModelPlayer md = null;
        if(killer instanceof Bullet){
            md = ((Bullet) killer).getColor();
        }
        if(killer instanceof Dynamite){
            md=((Dynamite) killer).getColor();
        }
        if(killer instanceof Bullet && ((Bullet) killer).getColor() == color) return;
        playerStatistics.incrementDeadNumber();
        board.getTank(md).getStatistics().incrementKillNumber();
        isAlive = false;

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
    public boolean getIsActive(){return true;}
    public void update(float time) {
        magazine.update(time);

        if(!isAlive) {
            body.setAngularVelocity(0f);
            body.setLinearVelocity(new Vector2(0f,0f));
            return;
        }

        if(makeShoot && magazine.shoot()) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() + MathUtils.cos(angle) * getHeight()/2;
            float y = getY() + MathUtils.sin(angle) * getHeight()/2;
            board.addObject(new Bullet(x, y, this));
            board.addBulletShoot(new BulletShoot(x, y, this));
        }

        if(placeDynamite && magazine.hasDynamite()) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() - MathUtils.cos(angle) * getHeight() * 1.1f;
            float y = getY() - MathUtils.sin(angle) * getHeight() * 1.1f;
            board.addObject(new Dynamite(x, y, this));
            magazine.placeDynamite();
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
    public Magazine getMagazine()
    {
        return magazine;
    }
}