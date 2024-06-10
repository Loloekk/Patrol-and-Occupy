package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.DynamiteDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class DynamiteDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    DynamiteDescription desc;
    int priority;
    public DynamiteDrawing(RegionPainter painter, DynamiteDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 5000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("dynamite"),desc);
    }
}
