package com.pao.game.model;

public class Magazine {
    private int capacity;
    private int quantity;
    private float shotCooldown;
    private float receiveCooldown;
    private float lastShoot;
    private float lastReceive;

    public Magazine(){
        capacity = 5;
        quantity = 5;
        shotCooldown = 0.5f;
        receiveCooldown = 2f;
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
    }
    public void update(float time){
        lastReceive+=time;
        lastShoot+=time;
        if(quantity == capacity) {
            lastReceive = 0;
        }
        if(lastReceive >= receiveCooldown)
        {
            lastReceive = 0;
            if(quantity < capacity)
                quantity++;
        }
    }

    public boolean shoot()
    {
        if(quantity == 0 || lastShoot < shotCooldown){
            return false;
        }
        quantity --;
        lastShoot = 0;
        return true;
    }

}
