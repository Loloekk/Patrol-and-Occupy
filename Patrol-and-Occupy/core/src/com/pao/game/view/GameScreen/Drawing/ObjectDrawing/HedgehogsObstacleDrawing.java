package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.HedgehogsObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class HedgehogsObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    HedgehogsObstacleDescription desc;
    final float priority = DrawingConstants.getFloatConstant("HedgehogsObstacle.Priority");
    final float scale = DrawingConstants.getFloatConstant("HedgehogsObstacle.Scale");
    public HedgehogsObstacleDrawing(RegionPainter painter, HedgehogsObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("hedgehogs.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),scale);
    }
}
