package com.pao.game.Communication;


import com.pao.game.model.ModelPlayer;

public class TankParams extends ColoredParams{
    int bullets;
    int plates;
    public TankParams(ModelPlayer color, float width, float height, float x, float y, float rotation, int bullets, int plates)
    {
        super(color,width,height,x,y,rotation);
        this.bullets = bullets;
        this.plates = plates;
    }
    public int getBullets()
    {
        return bullets;
    }
    public int getPlates()
    {
        return plates;
    }

}
