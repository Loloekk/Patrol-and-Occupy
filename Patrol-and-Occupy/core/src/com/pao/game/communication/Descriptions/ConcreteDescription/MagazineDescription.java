package com.pao.game.communication.Descriptions.ConcreteDescription;

import com.pao.game.communication.Descriptions.ColoredObjectDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;

public class MagazineDescription extends ColoredObjectDescription {
    int bullets;
    int plates;
    boolean dynamite;
    public MagazineDescription() {
        super();
        bullets = 0;
        plates = 0;
        dynamite = false;
    }
    public MagazineDescription setBullets(int bullets) {this.bullets = bullets; return this;}
    public MagazineDescription setPlates(int plates) {this.plates = plates; return this;}
    public MagazineDescription setDynamite(boolean dynamite) {this.dynamite = dynamite; return this;}
    public int getBullets()
    {
        return bullets;
    }
    public int getPlates()
    {
        return plates;
    }
    public boolean getDynamite()
    {
        return dynamite;
    }
}
