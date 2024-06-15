package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.BrakeObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BrakeObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BrakeObstacleDescription desc;
    final float priority = DrawingConstants.getFloatConstant("BrakeObstacle.Priority");
    final float scale = DrawingConstants.getFloatConstant("BrakeObstacle.Scale");
    public BrakeObstacleDrawing(RegionPainter painter, BrakeObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("brake.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),scale);
    }
}
