package com.pao.game.Communication;

import com.pao.game.model.ModelPlayer;

public class ColoredParams extends Params {
    ModelPlayer color;
    public ColoredParams(ModelPlayer color, float widht, float height, float x, float y, float rotation){
        super(widht, height, x, y, rotation);
        this.color=color;
    }
    public ModelPlayer getColor() {
        return color;
    }
}