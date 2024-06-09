package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BulletDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BulletDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BulletDescription desc;
    int priority;
    public BulletDrawing(RegionPainter painter, BulletDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 7000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getBulletTexture(),desc);
    }
}
