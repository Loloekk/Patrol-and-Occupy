package com.pao.game.model;

public class Magazine {
    private int quantity;
    private float lastShoot;
    private float lastReceive;
    ModelSettings settings;

    public Magazine(ModelSettings settings){
        this.settings = settings;
        quantity = settings.getMagazineCapacity();
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
    }
    public void update(float time){
        lastReceive+=time;
        lastShoot+=time;
        if(quantity == settings.getMagazineCapacity()) {
            lastReceive = 0;
        }
        if(lastReceive >= settings.getReceiveCooldown())
        {
            lastReceive = 0;
            if(quantity < settings.getMagazineCapacity())
                quantity++;
        }
    }

    public boolean shoot()
    {
        if(quantity == 0 || lastShoot < settings.getShootCooldown()){
            System.out.println(settings.getShootCooldown());
            return false;
        }
        quantity --;
        lastShoot = 0;
        return true;
    }

}
