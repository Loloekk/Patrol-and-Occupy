package com.pao.game.communication.Descriptions;

import com.pao.game.model.ModelPlayer;

public abstract class ColoredObjectDescription extends ObjectDescription{
    ModelPlayer color;
    public ColoredObjectDescription() {
        super();
        color = null;
    }
    public ColoredObjectDescription setColor(ModelPlayer color) {this.color = color; return this;}
    public ModelPlayer getColor() {return color;}
}
