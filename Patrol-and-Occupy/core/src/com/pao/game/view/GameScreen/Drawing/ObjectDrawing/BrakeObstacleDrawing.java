package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BrakeObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BrakeObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BrakeObstacleDescription desc;
    int priority;
    public BrakeObstacleDrawing(RegionPainter painter, BrakeObstacleDescription desc){
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
        painterGame.drawTexture(Textures.getTexture("brake.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),1.3f);
    }
}
