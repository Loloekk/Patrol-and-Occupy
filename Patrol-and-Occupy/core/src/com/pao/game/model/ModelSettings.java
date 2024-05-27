package com.pao.game.model;

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
        }
    }
    public static void setDefault()
    {
        gameTime = 20f;
        numberOfPlayers = 2;
        map = 1;
        width = 1920;
        height = 954;
        tankSpeed = 8f;
        bulletSpeed = 14f;
        rotateSpeed = 4f;
        magazineCapacity = 5;
        shootCooldown = 0.2f;
        receiveCooldown = 2f;
    }
    /*public void setGameTime(float state){gameTime = state;}
    public void setNumberOfPlayers(int state){numberOfPlayers = state;}
    public void setMap(int state){map = state;}
    public void setTankSpeed(float state){tankSpeed = state;}
    public void setBulletSpeed(float state){bulletSpeed = state;}
    public void setMagazineCapacity(int state){magazineCapacity = state;}
    public void setShootCooldown(float state){shootCooldown = state;}
    public void setReceiveCooldown(float state){receiveCooldown = state;}*/
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

}
