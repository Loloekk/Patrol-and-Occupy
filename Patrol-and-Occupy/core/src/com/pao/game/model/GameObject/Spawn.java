package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.ModelPlayer;

public class Spawn extends BodyGameObject {
    float COOLDOWN = 5;
    static final int width = 160;
    static final int height = 160;
    ModelPlayer color;
    Tank tank;
    float cooldown=0;
    public Spawn(float x, float y, Tank tank) {
        super(x, y, width, height, 0, BodyDef.BodyType.StaticBody, tank.getBody().getWorld(), 1f, false);
        body.setUserData(this);
        this.color = tank.getColor();
        this.tank = tank;
    }
    @Override
    public void update(float time)
    {
        if(!tank.getIsAlive()){
            cooldown+=time;
        }
        if(cooldown > COOLDOWN)
        {
            cooldown = 0;
            tank.revive();
        }
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
