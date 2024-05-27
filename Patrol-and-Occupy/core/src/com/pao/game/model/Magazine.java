package com.pao.game.model;

public class Magazine {
    private int quantity;
    private float lastShoot;
    private float lastReceive;
    public Magazine(){
        quantity = ModelSettings.getMagazineCapacity();
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
    }
    public void update(float time){
        lastReceive+=time;
        lastShoot+=time;
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
            System.out.println(ModelSettings.getShootCooldown());
            return false;
        }
        quantity --;
        lastShoot = 0;
        return true;
    }

}
