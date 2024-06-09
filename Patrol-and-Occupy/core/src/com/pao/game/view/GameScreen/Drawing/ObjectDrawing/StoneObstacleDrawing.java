package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.StoneObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class StoneObstacleDrawing implements ObjectDrawing{
    RegionPainter painter;
    StoneObstacleDescription desc;
    int priority;
    public StoneObstacleDrawing(RegionPainter painter, StoneObstacleDescription desc){
        this.painter = painter;
        this.desc = desc;
        priority = 7000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painter.drawTexture(Textures.getStoneObstacleTexture(),
                new ObjectDescription(desc).setHeight(desc.getHeight()+1).setWidth(desc.getWidth()+1));
    }
}
