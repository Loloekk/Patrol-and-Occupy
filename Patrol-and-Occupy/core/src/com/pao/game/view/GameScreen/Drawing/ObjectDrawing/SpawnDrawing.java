package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;
import com.pao.game.constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.SpawnDescription;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

public class SpawnDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    SpawnDescription desc;
    final float priority = DrawingConstants.getFloatConstant("Spawn.Priority");
    final float scale = DrawingConstants.getFloatConstant("Spawn.Scale");
    public SpawnDrawing(RegionPainter painter,  SpawnDescription desc){
        this.painterGame = painter;
        this.desc = desc;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("spawn",desc.getColor()),desc,scale);
    }
}
