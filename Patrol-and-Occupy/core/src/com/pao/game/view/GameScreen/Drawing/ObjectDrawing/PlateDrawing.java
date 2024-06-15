package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.PlateDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class PlateDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    PlateDescription desc;
    final float priority = DrawingConstants.getFloatConstant("Plate.Priority");
    final float scale = DrawingConstants.getFloatConstant("Plate.Scale");
    public PlateDrawing(RegionPainter painter, PlateDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("plate",desc.getColor()),desc,scale);
    }
}
