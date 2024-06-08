package com.pao.game.model.GameObject.CreatingParams;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.ModelPlayer;

public class CreatingParams {
    float X;
    float Y;
    float rotation;
    ModelPlayer color;
    BodyDef.BodyType bodyType;
    float density;
    boolean isSensor;
    public CreatingParams(){
        X=0;
        Y=0;
        rotation = 0;
        color = null;
        bodyType = BodyDef.BodyType.DynamicBody;
        density = 1f;
        isSensor = false;
    }
    public CreatingParams setX(float X)
    {
        this.X=X;
        return this;
    }
    public CreatingParams setY(float Y)
    {
        this.Y=Y;
        return this;
    }
    public CreatingParams setRotation(float rotation)
    {
        this.rotation=rotation;
        return this;
    }
    public CreatingParams setColor(ModelPlayer color)
    {
        this.color=color;
        return this;
    }
    public CreatingParams setBodyType(BodyDef.BodyType bodyType)
    {
        this.bodyType=bodyType;
        return this;
    }
    public CreatingParams setDensity(float density)
    {
        this.density=density;
        return this;
    }
    public CreatingParams setIsSensor(boolean isSensor)
    {
        this.isSensor = isSensor;
        return this;
    }
    public float getX(){
        return X;
    }
    public float getY(){
        return Y;
    }
    public float getRotation(){
        return rotation;
    }
    public ModelPlayer getColor(){
        return color;
    }
    public BodyDef.BodyType getBodyType(){
        return bodyType;
    }
    public float getDensity(){
        return density;
    }
    public boolean getIsSensor(){
        return isSensor;
    }
    public BodyGameObject create(Board board)
    {
        return null;
    }
}
