package com.pao.game.communication.Descriptions.ConcreteDescription;

import com.pao.game.communication.Descriptions.ColoredObjectDescription;

public class TankDescription extends ColoredObjectDescription {
    int bullets;
    int plates;
    boolean isAlive;
    boolean dynamite;
    public TankDescription() {
        super();
        bullets = 0;
        plates = 0;
        isAlive = false;
        dynamite = false;
    }
    public TankDescription setBullets(int bullets) {this.bullets = bullets; return this;}
    public TankDescription setPlates(int plates) {this.plates = plates; return this;}
    public TankDescription setIsAlive(boolean isAlive) {this.isAlive = isAlive; return this;}
    public TankDescription setDynamite(boolean dynamite) {this.dynamite = dynamite; return this;}
    public int getBullets()
    {
        return bullets;
    }
    public int getPlates()
    {
        return plates;
    }
    public boolean getIsAlive()
    {
        return isAlive;
    }
    public boolean getDynamite()
    {
        return dynamite;
    }
}
