package com.pao.game.model;

import com.pao.game.Communication.Move;

import java.util.List;

public interface Board {

    void update(float t);
    float getRemainingTime();
    void setmove(ModelPlayer color, Move move, boolean value);
    boolean checkBoardCollision(GameObject gameObject);
    boolean checkBulletCollision(GameObject gameObject);
    boolean checkTankCollision(GameObject gameObject);
    boolean checkObstacleCollision(GameObject gameObject);
    boolean checkDynamiteCollision(GameObject gameObject);
    boolean checkSpawnCollision(GameObject gameObject);
    boolean checkBreakableObstacleCollision(GameObject gameObject);
    List<Tank> getTankList();
    List<Bullet> getBulletList();
    List<Obstacle> getObstacleList();
    List<Plate> getPlateList();
    List<Dynamite> getDynamiteList();
    List<Spawn> getSpawnList();
    List<BreakableObstacle> getBreakableObstacleList();
    int getWidth();
    int getHeight();
    void addBullet(Bullet bullet);
    void addDynamite(Dynamite dynamite);
}