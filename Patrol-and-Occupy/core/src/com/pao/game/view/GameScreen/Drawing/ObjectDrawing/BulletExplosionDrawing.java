package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BulletExplosionDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class BulletExplosionDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletExplosionDescription desc;
    int priority;
    public BulletExplosionDrawing(RegionPainter painter, BulletExplosionDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 30000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getFrame("bullet.Explosion",desc.getStateTime()),desc,1.5f);
    }
}
