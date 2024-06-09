package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.PlateDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class PlateDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    PlateDescription desc;
    int priority;
    public PlateDrawing(RegionPainter painter, PlateDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 1000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getPlateTexture(desc.getColor()),desc);
    }
}
