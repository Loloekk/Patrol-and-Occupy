package com.pao.game.model.Boards;

import com.pao.game.communication.Move;
import com.pao.game.model.*;

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
    Tank getTank(ModelPlayer color);
    int getWidth();
    int getHeight();
    void addBullet(Bullet bullet);
    void addDynamite(Dynamite dynamite);
    void destroyTank(Tank tank, ModelPlayer killer);
    void destroyBullet(Bullet bullet);
    void destroyBreakableObstacle(BreakableObstacle breakableObstacle);
    void destroyDynamite(Dynamite dynamite, ModelPlayer killer);
    void changePlateOwner(Plate plate, Tank owner);
}