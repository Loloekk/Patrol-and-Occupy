package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;
import com.pao.game.communication.Descriptions.ConcreteDescription.SpawnDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class SpawnDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    SpawnDescription desc;
    int priority;
    public SpawnDrawing(RegionPainter painter,  SpawnDescription desc){
        this.painterGame = painter;
        this.desc = desc;
        priority = 2000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getSpawnTexture(desc.getColor()),desc);
    }
}
