package com.pao.game.viewmodel;

import com.pao.game.model.ModelSettings;
import com.pao.game.model.Options;

public class EditSettings {
    ModelSettings settings;
    public EditSettings()
    {
        settings = new ModelSettings();
    }
    public void setOption(Options o, float state)
    {
        settings.setOption(o,state);
    }
    public void setOption(Options o, int state)
    {
        settings.setOption(o,state);
    }
    public float getGameTime(){return settings.getGameTime();}
    public int getNumberOfPlayers(){return settings.getNumberOfPlayers();}
    public int getMap(){return settings.getMap();}
    public float getTankSpeed(){return settings.getTankSpeed();}
    public float getBulletSpeed(){return settings.getBulletSpeed();}
    public int getMagazineCapacity(){return settings.getMagazineCapacity();}
    public float getShootCooldown(){return settings.getShootCooldown();}
    public float getReceiveCooldown(){return settings.getReceiveCooldown();}
    public int getWidth(){return settings.getWidth();}
    public int getHeight(){return settings.getHeight();}
    public ModelSettings getSettings(){return settings;}

}
