package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.StoneObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class StoneObstacleDrawing implements ObjectDrawing{
    RegionPainter painter;
    StoneObstacleDescription desc;
    final float priority = DrawingConstants.getFloatConstant("StoneObstacle.Priority");
    final float scale = DrawingConstants.getFloatConstant("StoneObstacle.Scale");
    public StoneObstacleDrawing(RegionPainter painter, StoneObstacleDescription desc){
        this.painter = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painter.drawTexture(Textures.getTexture("stone.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),scale);
    }
}
