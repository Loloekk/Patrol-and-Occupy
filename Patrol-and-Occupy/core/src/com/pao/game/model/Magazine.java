package com.pao.game.model;

public class Magazine {
    private int quantity;
    private float lastShoot;
    private float lastReceive;
    float lastPlaceDynamite;
    public Magazine(){
        quantity = ModelSettings.getMagazineCapacity();
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
        lastPlaceDynamite = 0;
    }
    public void update(float time){
        lastReceive+=time;
        lastShoot+=time;
        lastPlaceDynamite+=time;
        if(quantity == ModelSettings.getMagazineCapacity()) {
            lastReceive = 0;
        }
        if(lastReceive >= ModelSettings.getReceiveCooldown())
        {
            lastReceive = 0;
            if(quantity < ModelSettings.getMagazineCapacity())
                quantity++;
        }
    }

    public boolean shoot()
    {
        if(quantity == 0 || lastShoot < ModelSettings.getShootCooldown()){
            return false;
        }
        quantity --;
        lastShoot = 0;
        return true;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public boolean hasDynamite()
    {
        if(lastPlaceDynamite > 3.0f){
            return true;
        }
        return false;
    }
    public void placeDynamite()
    {
        lastPlaceDynamite = 0;
    }

}
