package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.UnbreakableObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class UnbreakableObstacleDrawing implements ObjectDrawing{
    RegionPainter painter;
    UnbreakableObstacleDescription desc;
    int priority;
    public UnbreakableObstacleDrawing(RegionPainter painter, UnbreakableObstacleDescription desc){
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
        painter.drawTexture(Textures.getUnbreakableObstacleTexture(),
                new ObjectDescription(desc).setHeight(desc.getHeight()+2).setWidth(desc.getWidth()+2));
    }
}
