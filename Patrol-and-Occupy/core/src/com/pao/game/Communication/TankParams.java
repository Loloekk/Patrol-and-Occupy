package com.pao.game.Communication;


import com.pao.game.model.ModelPlayer;

public class TankParams extends ColoredParams{
    int bullets;
    int plates;
    boolean isAlive;
    public TankParams(ModelPlayer color, float width, float height, float x, float y, float rotation, int bullets, int plates,boolean isAlive)
    {
        super(color,width,height,x,y,rotation);
        this.bullets = bullets;
        this.plates = plates;
        this.isAlive = isAlive;
    }
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

}
