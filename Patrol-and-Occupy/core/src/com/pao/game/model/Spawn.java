package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

//public class Spawn extends BodyGameObject{
public class Spawn extends PolygonGameObject{
    ModelPlayer color;
//    public Spawn(float x, float y, World world, ModelPlayer color) {
//        super(x, y, 160,160,0, BodyDef.BodyType.StaticBody, world, 1f, false);
//        this.color = color;
//    }
    public Spawn(float x, float y, ModelPlayer color) {
        super(x, y, 160,160);
        this.color = color;
    }

    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
