package com.pao.game.viewmodel;

import com.pao.game.model.ModelSettings;
import com.pao.game.communication.Options;

public class EditSettings {
    private EditSettings()
    {
        throw new UnsupportedOperationException("Cannot instantiate EditSettings class");
    }
    public static void setOption(Options o, float state)
    {
        ModelSettings.setOption(o,state);
    }
    public static void setOption(Options o, int state)
    {
        ModelSettings.setOption(o,state);
    }
    public static float getGameTime(){return ModelSettings.getGameTime();}
    public static int getNumberOfPlayers(){return ModelSettings.getNumberOfPlayers();}
    public static int getMap(){return ModelSettings.getMap();}
    public static float getTankSpeed(){return ModelSettings.getTankSpeed();}
    public static float getBulletSpeed(){return ModelSettings.getBulletSpeed();}
    public static float getRotateSpeed(){return ModelSettings.getRotateSpeed();}
    public static int getMagazineCapacity(){return ModelSettings.getMagazineCapacity();}
    public static float getShootCooldown(){return ModelSettings.getShootCooldown();}
    public static float getReceiveCooldown(){return ModelSettings.getReceiveCooldown();}
    public static int getWidth(){return ModelSettings.getWidth();}
    public static int getHeight(){return ModelSettings.getHeight();}
    public static boolean getControl(){return ModelSettings.getControl();}
}
