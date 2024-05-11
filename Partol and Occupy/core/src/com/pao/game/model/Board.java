package com.pao.game.model;

import com.pao.game.viewmodel.*;

public interface Board {
    void update(long t);
    void setstart(long t);
    void setmove(Color color, Move move, boolean value);
    boolean checkBoardCollision(GameObject gameObject);
    boolean checkBulletCollision(GameObject gameObject);
    boolean checkTankCollision(GameObject gameObject);
}
