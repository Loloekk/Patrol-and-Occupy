package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.DynamiteExplosionDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class DynamiteExplosionDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    DynamiteExplosionDescription desc;
    final float priority = DrawingConstants.getFloatConstant("DynamiteExplosion.Priority");
    final float scale = DrawingConstants.getFloatConstant("DynamiteExplosion.Scale");
    public DynamiteExplosionDrawing(RegionPainter painter, DynamiteExplosionDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getFrame("dynamite.Explosion",desc.getStateTime()),desc,scale);
    }
}
