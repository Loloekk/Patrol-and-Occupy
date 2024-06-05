package com.pao.game.model.GameObject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.ModelPlayer;
import com.pao.game.model.ModelSettings;

public class Bullet extends BodyGameObject {
    static final int width = 30;
    static final int height = 10;
    Board board;
    ModelPlayer color;
    boolean isAlive = true;
    public Bullet(float x, float y, Tank tank, World world){
        super(x, y, width, height, tank.getRotation(), BodyDef.BodyType.DynamicBody, world, 1f, false);
        body.setUserData(this);
        color = tank.getColor();
        board = tank.board;
    }
    public void update(float t){
        Vector2 vel = body.getLinearVelocity();
        vel.x = ModelSettings.getBulletSpeed() * MathUtils.cos(body.getAngle());
        vel.y = ModelSettings.getBulletSpeed() * MathUtils.sin(body.getAngle());
        body.setLinearVelocity(vel);
    }
    public void takeDamage(){
        isAlive = false;
    }
    public ModelPlayer getColor(){
        return color;
    }
    public boolean getIsAlive() { return isAlive; }
}