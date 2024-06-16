package com.pao.game.model.GameObject.Others.Tank.Magazine;

import com.badlogic.gdx.math.MathUtils;
import com.pao.game.communication.Descriptions.ConcreteDescription.MagazineDescription;
import com.pao.game.communication.Move;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Explosions.BulletShoot.BulletShootCreatingParams;
import com.pao.game.model.GameObject.Others.Bullet.BulletCreatingParams;
import com.pao.game.model.GameObject.Others.Dynamite.DynamiteCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelSettings;

public class Magazine5Buttons implements Magazine{
    Tank tank;
    Board board;
    boolean makeShoot;
    boolean placeDynamite;
    private int quantity;
    private float lastShoot;
    private float lastReceive;
    float lastPlaceDynamite;
    public Magazine5Buttons(Tank tank)
    {
        this.tank = tank;
        this.board = tank.getBoard();
        quantity = ModelSettings.getMagazineCapacity();
        makeShoot = false;
        placeDynamite = false;
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
        lastPlaceDynamite = 0;
    }

    @Override
    public void setMove(Move move, boolean state) {
        switch (move) {
            case Shoot:
                makeShoot = state;
                break;
            case Dynamite:
                placeDynamite = state;
                break;
        }
    }
    @Override
    public void update(float time) {
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
        if(tank.getIsAlive() && makeShoot && quantity > 0 && lastShoot >= ModelSettings.getShootCooldown()) {
            quantity --;
            lastShoot = 0;
            float angle = tank.getRotation() * MathUtils.degreesToRadians;
            float x = tank.getX() + MathUtils.cos(angle) * tank.getHeight() * 0.5f;
            float y = tank.getY() + MathUtils.sin(angle) * tank.getWidth() * 0.5f;
            board.addObjectToCreate(new BulletCreatingParams().setX(x).setY(y).setColor(tank.getColor()).setRotation(tank.getRotation()));
            board.addObjectToCreate(new BulletShootCreatingParams().setTank(tank).setColor(tank.getColor()).setX(x).setY(y).setRotation(tank.getRotation()));
        }
        if(tank.getIsAlive()&& placeDynamite && lastPlaceDynamite > ModelSettings.getDynamiteCooldown()){
            lastPlaceDynamite = 0;
            float angle = tank.getRotation() * MathUtils.degreesToRadians;
            float x = tank.getX() - MathUtils.cos(angle) * tank.getHeight() * 1.1f;
            float y = tank.getY() - MathUtils.sin(angle) * tank.getWidth() * 1.1f;
            board.addObjectToCreate(new DynamiteCreatingParams().setX(x).setY(y).setRotation(tank.getRotation()));
        }
    }
    @Override
    public boolean hasDynamite()
    {
        if(lastPlaceDynamite > ModelSettings.getDynamiteCooldown()){
            return true;
        }
        return false;
    }

    @Override
    public MagazineDescription getDescription() {
        return new MagazineDescription().setBullets(quantity).setDynamite(hasDynamite());
    }
}
