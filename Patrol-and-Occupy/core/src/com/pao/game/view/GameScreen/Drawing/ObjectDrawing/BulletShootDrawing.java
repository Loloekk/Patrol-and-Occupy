package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.BulletShootDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class BulletShootDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletShootDescription desc;
    final float priority = DrawingConstants.getFloatConstant("BulletShoot.Priority");
    final float scale = DrawingConstants.getFloatConstant("BulletShoot.Scale");
    public BulletShootDrawing(RegionPainter painter, BulletShootDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getFrame("bullet.Shoot",desc.getStateTime()),desc,scale);
    }
}
