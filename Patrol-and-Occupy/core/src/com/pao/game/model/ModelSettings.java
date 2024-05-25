package com.pao.game.model;

public class ModelSettings {
    private float gameTime;
    private int numberOfPlayers;
    private int map;
    private int width;
    private int height;
    private float tankSpeed;
    private float bulletSpeed;
    private int magazineCapacity;
    private float shootCooldown;
    private float receiveCooldown;
    public ModelSettings()
    {
        gameTime = 120f;
        numberOfPlayers = 2;
        map = 1;
        width = 1720;
        height = 954;
        tankSpeed = 8f;
        bulletSpeed = 700f;
        magazineCapacity = 5;
        shootCooldown = 0.2f;
        receiveCooldown = 2f;
    }
    public void setOption(Options option, float state){
        switch (option){
            case gameTime:
                this.gameTime = state;
                break;
            case numberOfPlayers:
                this.numberOfPlayers = (int)state;
                break;
            case map:
                this.map=(int) state;
                break;
            case tankSpeed:
                this.tankSpeed = state;
                break;
            case bulletSpeed:
                this.bulletSpeed = state;
                break;
            case magazineCapacity:
                this.magazineCapacity = (int)state;
                break;
            case shootCooldown:
                this.shootCooldown = state;
                break;
            case receiveCooldown:
                this.receiveCooldown = state;
                break;
        }
    }
    public void setOption(Options option, int state){
        switch (option){
            case gameTime:
                this.gameTime = (float)state;
                break;
            case numberOfPlayers:
                this.numberOfPlayers = state;
                break;
            case map:
                this.map= state;
                break;
            case tankSpeed:
                this.tankSpeed = (float)state;
                break;
            case bulletSpeed:
                this.bulletSpeed = (float)state;
                break;
            case magazineCapacity:
                this.magazineCapacity = state;
                break;
            case shootCooldown:
                this.shootCooldown = (float)state;
                break;
            case receiveCooldown:
                this.receiveCooldown = (float)state;
                break;
        }
    }
    /*public void setGameTime(float state){gameTime = state;}
    public void setNumberOfPlayers(int state){numberOfPlayers = state;}
    public void setMap(int state){map = state;}
    public void setTankSpeed(float state){tankSpeed = state;}
    public void setBulletSpeed(float state){bulletSpeed = state;}
    public void setMagazineCapacity(int state){magazineCapacity = state;}
    public void setShootCooldown(float state){shootCooldown = state;}
    public void setReceiveCooldown(float state){receiveCooldown = state;}*/
    public float getGameTime(){return gameTime;}
    public int getNumberOfPlayers(){return numberOfPlayers;}
    public int getMap(){return map;}
    public float getTankSpeed(){return tankSpeed ;}
    public float getBulletSpeed(){return bulletSpeed;}
    public int getMagazineCapacity(){return magazineCapacity;}
    public float getShootCooldown(){return shootCooldown;}
    public float getReceiveCooldown(){return receiveCooldown;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}

}
