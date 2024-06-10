package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.BricksObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class BricksObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    BricksObstacleDescription desc;
    int priority;
    public BricksObstacleDrawing(RegionPainter painter, BricksObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 7600;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("bricks.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()+1).setWidth(desc.getWidth()+1));
    }
}
