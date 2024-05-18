package com.pao.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.viewmodel.Params;

public class RegionPainter {
    Batch batch;
    float originX;
    float originY;
    float width;
    float height;
    float painterWidth;
    float painterHeight;
    float scaleWidth;
    float scaleHeight;
    Color backgorundColor;
    Texture backgroundTexture;
    public RegionPainter(Batch batch, float originX, float originY, float width, float height, float painterWidth, float painterHeight, Color color)
    {
        this.batch = batch;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.painterHeight = painterHeight;
        this.painterWidth = painterWidth;
        this.backgorundColor = color;
        scaleWidth = height/painterHeight;
        scaleHeight = width/painterWidth;
        Pixmap pixmap = new Pixmap((int)width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgorundColor);
        pixmap.fillRectangle(0, 0, (int)width, (int)height);
        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();
    }
    public void fillBackground()
    {
        batch.draw(new TextureRegion(backgroundTexture),originX,originY,width,height);
    }
    public void draw(TextureRegion texture, float x, float y, float W, float H,float R) {
        batch.draw(texture,(x-H/2)*scaleHeight+originX,(y-W/2)*scaleWidth+originY,
                H/2*scaleHeight,W/2*scaleWidth,H*scaleHeight,W*scaleWidth,1,1,R);
    }
    public void draw(TextureRegion texture, Params object) {
        batch.draw(texture,(object.getX()- object.getWidht()/2)*scaleWidth+originX,(object.getY()- object.getHeight()/2)*scaleHeight+originY,
                object.getWidht()/2*scaleWidth,object.getHeight()/2*scaleHeight,(object.getWidht())*scaleWidth,(object.getHeight())*scaleHeight,1,1,object.getRotation());
    }
    public void draw(TextureRegion texture, Params object,float add) {
        batch.draw(texture,(object.getX()- object.getWidht()/2)*scaleWidth+originX,(object.getY()- object.getHeight()/2)*scaleHeight+originY,
                object.getWidht()/2*scaleWidth,object.getHeight()/2*scaleHeight,(object.getWidht()+add)*scaleWidth,(object.getHeight()+add)*scaleHeight,1,1,object.getRotation());
    }
    public void dispose()
    {
        backgroundTexture.dispose();
    }
}
