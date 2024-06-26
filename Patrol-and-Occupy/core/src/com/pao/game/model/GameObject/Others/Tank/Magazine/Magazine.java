package com.pao.game.model.GameObject.Others.Tank.Magazine;

import com.pao.game.communication.Descriptions.ConcreteDescription.MagazineDescription;
import com.pao.game.communication.Move;

public interface Magazine {
    void setMove(Move move, boolean state);
    void update(float time);
    boolean hasDynamite();
    MagazineDescription getDescription();
}
