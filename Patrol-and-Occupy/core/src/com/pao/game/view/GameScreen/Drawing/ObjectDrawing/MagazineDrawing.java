package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.Constants.DrawingConstants;
import com.pao.game.communication.Descriptions.ConcreteDescription.MagazineDescription;
import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.EditSettings;

public class MagazineDrawing implements ObjectDrawing {
    final float MAGAZINE_WIDTH = DrawingConstants.getFloatConstant("Magazine.Width");
    final float MAGAZINE_HEIGHT = DrawingConstants.getFloatConstant("Magazine.Height");
    final float PLATE_X_OFF = DrawingConstants.getFloatConstant("Magazine.Plate.X.Off");
    final float PLATE_Y_OFF = DrawingConstants.getFloatConstant("Magazine.Plate.Y.Off");
    final float PLATE_TEXT_X_OFF = DrawingConstants.getFloatConstant("Magazine.Plate.Text.X.Off");
    final float PLATE_TEXT_Y_OFF = DrawingConstants.getFloatConstant("Magazine.Plate.Text.Y.Off");
    final float PLATE_WIDTH = DrawingConstants.getFloatConstant("Magazine.Plate.Width");
    final float PLATE_HEIGHT = DrawingConstants.getFloatConstant("Magazine.Plate.Height");
    final float BULLET_X_OFF = DrawingConstants.getFloatConstant("Magazine.Bullet.X.Off");
    final float BULLET_Y_OFF = DrawingConstants.getFloatConstant("Magazine.Bullet.Y.Off");
    final float BULLET_TEXT_X_OFF = DrawingConstants.getFloatConstant("Magazine.Bullet.Text.X.Off");
    final float BULLET_TEXT_Y_OFF = DrawingConstants.getFloatConstant("Magazine.Bullet.Text.Y.Off");
    final float BULLET_WIDTH = DrawingConstants.getFloatConstant("Magazine.Bullet.Width");
    final float BULLET_HEIGHT = DrawingConstants.getFloatConstant("Magazine.Bullet.Height");
    final float DYNAMITE_X_OFF = DrawingConstants.getFloatConstant("Magazine.Dynamite.X.Off");
    final float DYNAMITE_Y_OFF = DrawingConstants.getFloatConstant("Magazine.Dynamite.Y.Off");
    final float DYNAMITE_WIDTH = DrawingConstants.getFloatConstant("Magazine.Dynamite.Width");
    final float DYNAMITE_HEIGHT = DrawingConstants.getFloatConstant("Magazine.Dynamite.Height");


    RegionPainter painterTop;
    float x;
    float y;
    final float priority = DrawingConstants.getFloatConstant("Magazine.Priority");
    MagazineDescription desc;
    public MagazineDrawing(RegionPainter topPainter, MagazineDescription desc){
        this.painterTop = topPainter;
        this.desc = desc;
        x=DrawingConstants.getFloatConstant("Magazine.X."+desc.getColor());
        y=DrawingConstants.getFloatConstant("Magazine.Y."+desc.getColor());
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painterTop.drawTexture(Textures.getTexture("magazine",desc.getColor()), new ObjectDescription().setX(x).setY(y).setWidth(MAGAZINE_WIDTH).setHeight(MAGAZINE_HEIGHT));
        painterTop.drawTexture(Textures.getTexture("plate",null),new ObjectDescription().setX(x+PLATE_X_OFF).setY(y+PLATE_Y_OFF).setWidth(PLATE_WIDTH).setHeight(PLATE_HEIGHT));
        painterTop.drawTexture(Textures.getTexture("bullet"),new ObjectDescription().setX(x+BULLET_X_OFF).setY(y+BULLET_Y_OFF).setWidth(BULLET_WIDTH).setHeight(BULLET_HEIGHT));
        if(desc.getDynamite()){
            painterTop.drawTexture(Textures.getTexture("dynamite"),new ObjectDescription().setX(x+DYNAMITE_X_OFF).setY(y+DYNAMITE_Y_OFF).setWidth(DYNAMITE_WIDTH).setHeight(DYNAMITE_HEIGHT));
        }
        painterTop.drowWriting("Plates",((Integer)desc.getPlates()).toString(),x+PLATE_TEXT_X_OFF,y+PLATE_TEXT_Y_OFF);
        painterTop.drowWriting("Bullets",(Integer)desc.getBullets()+"/"+ EditSettings.getMagazineCapacity(),x+BULLET_TEXT_X_OFF,y+BULLET_TEXT_Y_OFF);

    }
}
