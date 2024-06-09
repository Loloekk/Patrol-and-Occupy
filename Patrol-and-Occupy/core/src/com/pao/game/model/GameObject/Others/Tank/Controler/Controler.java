package com.pao.game.model.GameObject.Others.Tank.Controler;

import com.pao.game.communication.Move;
import com.pao.game.model.GameObject.Others.Tank.Magazine.Magazine;

public interface Controler {
    void setMove(Move move, boolean state);
    void update(float time);
    Magazine getMagazine();
}
