package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.HedgehogsObstacleDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class HedgehogsObstacleDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    HedgehogsObstacleDescription desc;
    int priority;
    public HedgehogsObstacleDrawing(RegionPainter painter, HedgehogsObstacleDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 7400;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("hedgehogs.Obstacle"),
                new ObjectDescription(desc).setHeight(desc.getHeight()).setWidth(desc.getWidth()),1.2f);
    }
}
