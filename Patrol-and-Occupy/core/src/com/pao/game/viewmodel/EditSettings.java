package com.pao.game.viewmodel;

import com.pao.game.model.ModelSettings;
import com.pao.game.model.Options;

public class EditSettings {
    static ModelSettings settings;
    public static void setOption(Options o, float state)
    {
        settings.setOption(o,state);
    }
    public static void setOption(Options o, int state)
    {
        settings.setOption(o,state);
    }
    public static float getGameTime(){return settings.getGameTime();}
    public static int getNumberOfPlayers(){return settings.getNumberOfPlayers();}
    public static int getMap(){return settings.getMap();}
    public static float getTankSpeed(){return settings.getTankSpeed();}
    public static float getBulletSpeed(){return settings.getBulletSpeed();}
    public static int getMagazineCapacity(){return settings.getMagazineCapacity();}
    public static float getShootCooldown(){return settings.getShootCooldown();}
    public static float getReceiveCooldown(){return settings.getReceiveCooldown();}
    public static int getWidth(){return settings.getWidth();}
    public static int getHeight(){return settings.getHeight();}
    public static ModelSettings getSettings(){return settings;}

}
