package com.pao.game.model.Boards;

import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.*;

import java.util.List;

public interface Board {
    void update(float t);
    float getRemainingTime();
    void setMove(ModelPlayer color, Move move, boolean value);
    List<Tank> getTankList();
    List<Bullet> getBulletList();
    List<Obstacle> getObstacleList();
    List<Plate> getPlateList();
    List<Dynamite> getDynamiteList();
    List<Spawn> getSpawnList();
    List<BreakableObstacle> getBreakableObstacleList();
    List<DynamiteExplosion> getDynamiteExplosionList();
    List<BulletShoot> getBulletShootList();
    Tank getTank(ModelPlayer color);
    int getWidth();
    int getHeight();
    void addBullet(Bullet bullet);
    void addDynamite(Dynamite dynamite);
    void addDynamiteExplosion(DynamiteExplosion dynamiteExplosion);
    void addBulletShoot(BulletShoot bulletShoot);
    void changePlateOwner(Plate plate, Tank owner);
}