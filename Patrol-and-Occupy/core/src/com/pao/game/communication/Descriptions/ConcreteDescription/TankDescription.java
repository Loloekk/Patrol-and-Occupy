package com.pao.game.communication.Descriptions.ConcreteDescription;

import com.pao.game.communication.Descriptions.ColoredObjectDescription;

public class TankDescription extends ColoredObjectDescription {
    boolean isAlive;
    public TankDescription() {
        super();
        isAlive = false;
    }
    public TankDescription setIsAlive(boolean isAlive) {this.isAlive = isAlive; return this;}
    public boolean getIsAlive()
    {
        return isAlive;
    }
}
