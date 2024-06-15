package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.BulletDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BulletDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletDescription desc;
    final float priority = DrawingConstants.getFloatConstant("Bullet.Priority");
    final float scale = DrawingConstants.getFloatConstant("Bullet.Scale");
    public BulletDrawing(RegionPainter painter, BulletDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("bullet"),desc,scale);
    }
}
