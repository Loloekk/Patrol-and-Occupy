package com.pao.game.model;

import com.pao.game.viewmodel.*;
import java.util.List;

public interface Board {

    void update(float t);
    void setRemainingTime(float time);
    void addRemainingTime(float time);
    float getRemainingTime();
    void setmove(MyColor color, Move move, boolean value);
    boolean checkBoardCollision(GameObject gameObject);
    boolean checkBulletCollision(GameObject gameObject);
    boolean checkTankCollision(GameObject gameObject);
    boolean checkObstacleCollision(GameObject gameObject);
    boolean checkDynamiteCollision(GameObject gameObject);
    List<Tank> getTankList();
    List<Bullet> getBulletList();
    List<Obstacle> getObstacleList();
    List<Plate> getPlateList();
    List<Dynamite> getDynamiteList();
    int getWidth();
    int getHeight();
    void addBullet(Bullet bullet);
    void addDynamite(Dynamite dynamite);
}