package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.DynamiteExplosionDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;

public class DynamiteExplosionDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    DynamiteExplosionDescription desc;
    int priority;
    public DynamiteExplosionDrawing(RegionPainter painter, DynamiteExplosionDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 10000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Animations.getDynamiteExplosionAnimation().getKeyFrame(((desc).getStateTime())),desc,2f);
    }
}
