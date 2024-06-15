package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.DynamiteDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class DynamiteDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    DynamiteDescription desc;
    final float priority = DrawingConstants.getFloatConstant("Dynamite.Priority");
    final float scale = DrawingConstants.getFloatConstant("Dynamite.Scale");
    public DynamiteDrawing(RegionPainter painter, DynamiteDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("dynamite"),desc,scale);
    }
}
