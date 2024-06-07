package com.pao.game.model.Boards;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.*;

import java.util.List;

public interface Board {
    void update(float t);
    float getRemainingTime();
    void setMove(ModelPlayer color, Move move, boolean value);
    List<BodyGameObject> getBodyObjects();
    List<Tank> getTankList();
    Tank getTank(ModelPlayer color);
    int getWidth();
    int getHeight();
    World getWorld();
    void addObject(BodyGameObject obj);
    void addDynamiteExplosion(DynamiteExplosion dynamiteExplosion);
    void addBulletShoot(BulletShoot bulletShoot);
    void addBulletExplosion(BulletExplosion bulletExplosion);
    List<DynamiteExplosion> getDynamiteExplosionList();
    List<BulletShoot> getBulletShootList();
    List<BulletExplosion> getBulletExplosionList();
}