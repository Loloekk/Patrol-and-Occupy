package com.pao.game.model.GameObject.Bodies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsCircle;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

import static com.pao.game.Constants.Box2dConstants.PPM;

public abstract class BodyGameObject implements GameObject {
    public Body body;
    float width;
    float height;
    public BodyGameObject(CreatingParamsRectangle CPR, World world) {
        body = BodyCreator.createBodyRectangle(CPR,world);
        this.width = CPR.getRealWidth();
        this.height = CPR.getRealHeight();
    }
    public BodyGameObject(CreatingParamsCircle CPC, World world) {
        body = BodyCreator.createBodyCircle(CPC, world);
        this.width = 2*CPC.getRadius();
        this.height = 2*CPC.getRadius();
    }
    public float getX() { return PPM * body.getPosition().x; }
    public float getY() { return PPM * body.getPosition().y; }
    public float getRotation() { return body.getAngle() * MathUtils.radiansToDegrees; }
    public Body getBody() { return body; }
    public void update(float t){}
    public void takeDamage(BodyGameObject obj) {}
    public boolean getIsActive(){return true;}
    public float getWidth(){ return width; }
    public float getHeight(){ return height; }
    public ObjectDescription getDescription(){return null;}
}
