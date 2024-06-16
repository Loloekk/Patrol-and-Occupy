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

public class MagazineOneButton implements Magazine{
    Tank tank;
    Board board;
    boolean makeShoot;
    private int quantity;
    private float lastShoot;
    private float lastReceive;
    float lastPlaceDynamite;
    boolean fullMagazine;
    public MagazineOneButton(Tank tank)
    {
        this.tank = tank;
        this.board = tank.getBoard();
        quantity = ModelSettings.getMagazineCapacity();
        makeShoot = false;
        lastShoot = Float.MAX_VALUE;
        lastReceive = Float.MAX_VALUE;
        lastPlaceDynamite = 0;
        fullMagazine = false;
    }

    @Override
    public void setMove(Move move, boolean state) {
        if(move == Move.Shoot) {
            makeShoot = state;
        }
    }

    @Override
    public void update(float time) {
        lastReceive+=time;
        lastShoot+=time;
        lastPlaceDynamite+=time;
        if(quantity == ModelSettings.getMagazineCapacity()) {
            lastReceive = 0;
            fullMagazine = true;
        }
        if(lastReceive >= ModelSettings.getReceiveCooldown())
        {
            lastReceive = 0;
            if(quantity < ModelSettings.getMagazineCapacity())
                quantity++;
        }
        if(makeShoot){
            makeShoot = false;
            if(tank.getIsAlive() && quantity > 0 && lastShoot >= ModelSettings.getShootCooldown()) {
                quantity --;
                lastShoot = 0;
                float angle = tank.getRotation() * MathUtils.degreesToRadians;
                float x = tank.getX() + MathUtils.cos(angle) * tank.getHeight() * 0.5f;
                float y = tank.getY() + MathUtils.sin(angle) * tank.getWidth() * 0.5f;
                board.addObjectToCreate(new BulletCreatingParams().setX(x).setY(y).setColor(tank.getColor()).setRotation(tank.getRotation()));
                board.addObjectToCreate(new BulletShootCreatingParams().setTank(tank).setColor(tank.getColor()).setX(x).setY(y).setRotation(tank.getRotation()));
            }
        }
        if(fullMagazine && quantity == 0){
        }
        if(tank.getIsAlive()&& fullMagazine && quantity == 0 && lastPlaceDynamite > ModelSettings.getDynamiteCooldown()){
            lastPlaceDynamite = 0;
            fullMagazine = false;
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
