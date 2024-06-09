package com.pao.game.model.GameObject.Others.Tank;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;
import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Bodies.PolyContainsPoly;
import com.pao.game.model.GameObject.Explosions.BulletExplosion.BulletExplosion;
import com.pao.game.model.GameObject.Explosions.BulletShoot.BulletShoot;
import com.pao.game.model.GameObject.Explosions.BulletShoot.BulletShootCreatingParams;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;
import com.pao.game.model.GameObject.Others.Bullet.BulletCreatingParams;
import com.pao.game.model.GameObject.Others.Dynamite.DynamiteCreatingParams;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;

import static com.pao.game.model.Constants.PPM;

public class Tank extends BodyGameObject {
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
    float width;
    float height;
    public Tank(TankCreatingParams TCP, Board board) {
        super(TCP,board.getWorld());
        body.setUserData(this);
        this.color = TCP.getColor();
        this.board = board;
        this.world = board.getWorld();
        this.isAlive = true;
        this.magazine = new Magazine();
        this.playerStatistics = new PlayerStatistics();
        width = TCP.getRealWidth();
        height = TCP.getRealHeight();
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
    private boolean isInSpawn(){
        return PolyContainsPoly.isPolyInPoly(body,spawn.body);
    }
    public void takeDamage(BodyGameObject killer)
    {
        if(!isAlive || isInSpawn()) return;

        if(!(killer instanceof Explosion)) return;

        if(killer instanceof BulletExplosion && ((BulletExplosion) killer).getColor() == color) return;
        if(killer instanceof BulletShoot) return;
        playerStatistics.incrementDeadNumber();
        board.getTank(((Explosion) killer).getColor()).getStatistics().incrementKillNumber();
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
    public Board getBoard(){return board;}
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public void setSpawn(Spawn spawn){this.spawn=spawn;}
    public void update(float time) {
        magazine.update(time);

        if(!isAlive) {
            body.setAngularVelocity(0f);
            body.setLinearVelocity(new Vector2(0f,0f));
            return;
        }

        if(makeShoot && magazine.shoot()) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() + MathUtils.cos(angle) * height * 0.5f;
            float y = getY() + MathUtils.sin(angle) * width * 0.5f;
            board.addObjectToCreate(new BulletCreatingParams().setX(x).setY(y).setColor(color).setRotation(getRotation()));
            board.addObjectToCreate(new BulletShootCreatingParams().setTank(this).setColor(color).setX(x).setY(y).setRotation(getRotation()));
        }

        if(placeDynamite && magazine.hasDynamite()) {
            float angle = getRotation() * MathUtils.degreesToRadians;
            float x = getX() - MathUtils.cos(angle) * height * 1.1f;
            float y = getY() - MathUtils.sin(angle) * width * 1.1f;
            board.addObjectToCreate(new DynamiteCreatingParams().setX(x).setY(y).setRotation(getRotation()));
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
    @Override
    public TankDescription getDescription()
    {
        return (TankDescription) (new TankDescription())
                .setBullets(magazine.getQuantity())
                .setPlates(playerStatistics.getNumberOfPlates())
                .setIsAlive(getIsAlive())
                .setDynamite(magazine.hasDynamite())
                .setColor(getColor())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}