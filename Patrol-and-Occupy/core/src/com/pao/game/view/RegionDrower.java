package com.pao.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.model.GameObject;
import com.pao.game.viewmodel.Params;

public class RegionDrower {
    float orginX;
    float orginY;
    float width;
    float height;
    float drowedWidth;
    float drowedHeight;
    float scaleWidth;
    float scaleHeight;
    Color backgorundColor;
    Texture backgroundTexture;
    public RegionDrower(float orginX, float orginY, float width, float height, float drowedWidth, float drowedHeight, Color color)
    {
        this.orginX = orginX;
        this.orginY = orginY;
        this.width = width;
        this.height = height;
        this.drowedHeight = drowedHeight;
        this.drowedWidth = drowedWidth;
        this.backgorundColor = color;
        scaleHeight = height/drowedHeight;
        scaleWidth = width/drowedWidth;
        Pixmap pixmap = new Pixmap((int)width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgorundColor);
        pixmap.fillRectangle(0, 0, (int)width, (int)height);
        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();
    }
    public void fillBackground(Batch batch)
    {
        batch.draw(new TextureRegion(backgroundTexture),orginX,orginY,width,height);
    }
    public void draw(Batch batch, TextureRegion texture, float x, float y, float W, float H,float R) {
        batch.draw(texture,(x-H/2)*scaleHeight,(y-W/2)*scaleWidth,H/2*scaleHeight,W/2*scaleWidth,H*scaleHeight,W*scaleWidth,1,1,R);
    }
    public void draw(Batch batch, TextureRegion texture, Params object) {
        batch.draw(texture,(object.getX()- object.getHeight()/2)*scaleHeight,(object.getY()- object.getWidht()/2)*scaleWidth,object.getHeight()/2*scaleHeight,object.getWidht()/2*scaleWidth,object.getHeight()*scaleHeight,object.getWidht()*scaleWidth,1,1,object.getRotation());
    }
    public void dispose()
    {
        backgroundTexture.dispose();
    }
}
