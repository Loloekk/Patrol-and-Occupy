package com.pao.game.model;

import com.pao.game.Constants.modelConstants;
import com.pao.game.communication.Options;

public class ModelSettings {
    private static float gameTime;
    private static int numberOfPlayers;
    private static int map;
    private static int width;
    private static int height;
    private static float tankSpeed;
    private static float bulletSpeed;
    private static float rotateSpeed;
    private static int magazineCapacity;
    private static float shootCooldown;
    private static float receiveCooldown;
    private static boolean oneButtonControl; // 0 / 1
    private static float respawnCooldown;
    private static float dynamiteCooldown;
    private ModelSettings()
    {
        throw new UnsupportedOperationException("Cannot instantiate ModelSettings class");
    }


    static {
        setDefault();
    }
    public static void setOption(Options option, float state){
        switch (option){
            case gameTime:
                gameTime = state;
                break;
            case numberOfPlayers:
                numberOfPlayers = (int)state;
                break;
            case map:
                map=(int) state;
                break;
            case tankSpeed:
                tankSpeed = state;
                break;
            case bulletSpeed:
                bulletSpeed = state;
                break;
            case rotateSpeed:
                rotateSpeed = state;
                break;
            case magazineCapacity:
                magazineCapacity = (int)state;
                break;
            case shootCooldown:
                shootCooldown = state;
                break;
            case receiveCooldown:
                receiveCooldown = state;
                break;
            case control:
                oneButtonControl = (int)state != 0;
                break;
            case respawnCooldown:
                respawnCooldown = state;
                break;
            case dynamiteCooldown:
                dynamiteCooldown = state;
                break;
        }
    }
    public static void setOption(Options option, int state){
        switch (option){
            case gameTime:
                gameTime = (float)state;
                break;
            case numberOfPlayers:
                numberOfPlayers = state;
                break;
            case map:
                map= state;
                break;
            case tankSpeed:
                tankSpeed = (float)state;
                break;
            case bulletSpeed:
                bulletSpeed = (float)state;
                break;
            case rotateSpeed:
                rotateSpeed = (float)state;
                break;
            case magazineCapacity:
                magazineCapacity = state;
                break;
            case shootCooldown:
                shootCooldown = (float)state;
                break;
            case receiveCooldown:
                receiveCooldown = (float)state;
                break;
            case control:
                oneButtonControl = state != 0;
                break;
            case respawnCooldown:
                respawnCooldown = (float)state;
                break;
            case dynamiteCooldown:
                dynamiteCooldown = (float)state;
                break;
        }
    }
    public static void setDefault()
    {
        gameTime = modelConstants.getConstant("default.GameTime");
        numberOfPlayers = (int) modelConstants.getConstant("default.NumberOfPlayers");
        map = (int) modelConstants.getConstant("default.Map");
        width = (int) modelConstants.getConstant("default.Width");
        height = (int) modelConstants.getConstant("default.Height");
        tankSpeed = modelConstants.getConstant("default.TankSpeed");
        bulletSpeed = modelConstants.getConstant("default.BulletSpeed");
        rotateSpeed = modelConstants.getConstant("default.RotateSpeed");
        magazineCapacity = (int) modelConstants.getConstant("default.MagazineCapacity");
        shootCooldown = modelConstants.getConstant("default.ShootCooldown");
        receiveCooldown = modelConstants.getConstant("default.ReceiveCooldown");
        oneButtonControl = modelConstants.getConstant("default.OneButtonControl")!=0;
        respawnCooldown = modelConstants.getConstant("default.RespawnCooldown");
        dynamiteCooldown = modelConstants.getConstant("default.DynamiteCooldown");
    }
    public static float getGameTime(){return gameTime;}
    public static int getNumberOfPlayers(){return numberOfPlayers;}
    public static int getMap(){return map;}
    public static float getTankSpeed(){return tankSpeed ;}
    public static float getBulletSpeed(){return bulletSpeed;}
    public static float getRotateSpeed(){return rotateSpeed;}
    public static int getMagazineCapacity(){return magazineCapacity;}
    public static float getShootCooldown(){return shootCooldown;}
    public static float getReceiveCooldown(){return receiveCooldown;}
    public static int getWidth(){return width;}
    public static int getHeight(){return height;}
    public static boolean getControl(){return oneButtonControl;}
    public static float getRespawnCooldown(){return respawnCooldown;}

    public static float getDynamiteCooldown(){return dynamiteCooldown;}

}
