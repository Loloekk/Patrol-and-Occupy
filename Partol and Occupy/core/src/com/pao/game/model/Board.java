package com.pao.game.model;

import com.pao.game.viewmodel.*;
import java.util.List;

public interface Board {

    void update(float t);
    void setstart(long t);
    void setmove(Color color, Move move, boolean value);
    boolean checkBoardCollision(GameObject gameObject);
    boolean checkBulletCollision(GameObject gameObject);
    boolean checkTankCollision(GameObject gameObject);
    List<Tank> getTankList();
    List<Bullet> getBulletList();
    int getWidth();
    int getHeight();
    void addBullet(Bullet bullet);
}
