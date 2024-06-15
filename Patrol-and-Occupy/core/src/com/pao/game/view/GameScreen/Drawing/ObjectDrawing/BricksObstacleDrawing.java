package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.BricksObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BricksObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BricksObstacleDescription desc;
    final float priority = DrawingConstants.getFloatConstant("BricksObstacle.Priority");
    final float scale = DrawingConstants.getFloatConstant("BricksObstacle.Scale");
    public BricksObstacleDrawing(RegionPainter painter, BricksObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("bricks.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),scale);
    }
}
