package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BulletShootDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class BulletShootDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletShootDescription desc;
    int priority;
    public BulletShootDrawing(RegionPainter painter, BulletShootDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 20000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getFrame("bullet.Shoot",desc.getStateTime()),desc,4f);
    }
}
