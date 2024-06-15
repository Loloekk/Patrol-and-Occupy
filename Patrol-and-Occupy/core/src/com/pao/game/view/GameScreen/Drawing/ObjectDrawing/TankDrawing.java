package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.EditSettings;

public class TankDrawing implements ObjectDrawing{
    RegionPainter painterGame;
    TankDescription desc;
    int priority;
    public TankDrawing(RegionPainter gamePainter, TankDescription desc){
        this.painterGame = gamePainter;
        this.desc = desc;
        priority = 6000;
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterGame.drawTexture(Textures.getTexture("tank",desc.getIsAlive() ? desc.getColor() : null),desc);
    }
}
