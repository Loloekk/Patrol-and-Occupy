package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.communication.Descriptions.ConcreteDescription.MagazineDescription;
import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.EditSettings;

public class MagazineDrawing implements ObjectDrawing {
    RegionPainter painterTop;
    float x;
    float y;
    int priority;
    MagazineDescription desc;
    public MagazineDrawing(RegionPainter topPainter, MagazineDescription desc){
        this.painterTop = topPainter;
        this.desc = desc;
        priority = 6000;
        x=0;
        y=50;
        if(desc.getColor() == ModelPlayer.Player1) {
            x = 227.5f;
        }
        if(desc.getColor() == ModelPlayer.Player2) {
            x = 1692.5f;
        }
        if(desc.getColor() == ModelPlayer.Player3) {
            x = 682.5f;
        }
        if(desc.getColor() == ModelPlayer.Player4) {
            x = 1237.5f;
        }
    }
    @Override
    public Integer getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterTop.drawTexture(Textures.getTexture("magazine",desc.getColor()), new ObjectDescription().setX(x).setY(y).setWidth(300).setHeight(95));
        painterTop.drawTexture(Textures.getTexture("plate",null),new ObjectDescription().setX(x-80).setY(y).setWidth(80).setHeight(80));
        painterTop.drawTexture(Textures.getTexture("bullet"),new ObjectDescription().setX(x+60).setY(y-10).setWidth(150).setHeight(50));
        if(desc.getDynamite()){
            painterTop.drawTexture(Textures.getTexture("dynamite"),new ObjectDescription().setX(x+120).setY(y+25).setWidth(30).setHeight(30));
        }
        painterTop.drowWriting("Plates",((Integer)desc.getPlates()).toString(),x-80,y);
        painterTop.drowWriting("Magazines",(Integer)desc.getBullets()+"/"+ EditSettings.getMagazineCapacity(),x+35,y-10);

    }
}
