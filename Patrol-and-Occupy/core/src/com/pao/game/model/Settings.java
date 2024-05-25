package com.pao.game.model;

public class Settings {
    private float gameTime;
    private int numberOfPlayers;
    private int map;
    private float tankSpeed;
    private float bulletSpeed;
    private int magazineCapacity;
    private float shootCooldown;
    private float receiveCooldown;
    public Settings()
    {
        gameTime = 120f;
        numberOfPlayers = 2;
        map = 1;
        tankSpeed = 8f;
        bulletSpeed = 700f;
        magazineCapacity = 5;
        shootCooldown = 0.2f;
        receiveCooldown = 2f;
    }
    public void setGameTime(float state){gameTime = state;}
    public void setNumberOfPlayers(int state){numberOfPlayers = state;}
    public void setMap(int state){map = state;}
    public void setTankSpeed(float state){tankSpeed = state;}
    public void setBulletSpeed(float state){bulletSpeed = state;}
    public void setMagazineCapacity(int state){magazineCapacity = state;}
    public void setShootCooldown(float state){shootCooldown = state;}
    public void setReceiveCooldown(float state){receiveCooldown = state;}
}
