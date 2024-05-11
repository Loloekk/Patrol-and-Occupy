package com.pao.game.model;

public interface Board {
    void update(long t);
    void setstart(long t);
    void setmove(color c, int move, boolean value);
    boolean checkBoardCollision(GameObject board);
    boolean checkBulletCollision(GameObject bullet);
    boolean checkTankCollision(GameObject tank);
}
