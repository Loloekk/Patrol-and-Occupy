package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.BulletExplosionDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class BulletExplosionDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletExplosionDescription desc;
    final float priority = DrawingConstants.getFloatConstant("BulletExplosion.Priority");
    final float scale = DrawingConstants.getFloatConstant("BulletExplosion.Scale");
    public BulletExplosionDrawing(RegionPainter painter, BulletExplosionDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getFrame("bullet.Explosion",desc.getStateTime()),desc,scale);
    }
}
