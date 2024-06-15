package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class TankDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    TankDescription desc;
    final float priority = DrawingConstants.getFloatConstant("Tank.Priority");
    final float scale = DrawingConstants.getFloatConstant("Tank.Scale");
    public TankDrawing(RegionPainter gamePainter, TankDescription desc){
        this.painterGame = gamePainter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("tank",desc.getIsAlive() ? desc.getColor() : null),desc,scale);
    }
}
