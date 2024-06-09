package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BreakableObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BreakableObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BreakableObstacleDescription desc;
    int priority;
    public BreakableObstacleDrawing(RegionPainter painter, BreakableObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 8000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getBreakableObstacleTexture(),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),1.14f);
    }
}
