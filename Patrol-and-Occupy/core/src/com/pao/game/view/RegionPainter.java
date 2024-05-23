package com.pao.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.viewmodel.Params;

import static com.pao.game.model.Constants.PPM;

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
        scaleWidth = width/painterWidth;
        scaleHeight = height/painterHeight;
        Pixmap pixmap = new Pixmap((int)width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgorundColor);
        pixmap.fillRectangle(0, 0, (int)width, (int)height);
        backgroundTexture = new Texture(pixmap);
        pixmap.dispose();
    }
    public void fillBackground()
    {
        batch.draw(new TextureRegion(backgroundTexture),originX/PPM,originY/PPM,width/PPM,height/PPM);
    }
    public void draw(TextureRegion texture, float x, float y, float W, float H,float R) {
        batch.draw(texture,(x-H/2)*scaleHeight+originX,(y-W/2)*scaleWidth+originY,
                H/2*scaleHeight,W/2*scaleWidth,H*scaleHeight,W*scaleWidth,1,1,R);
    }
    public void draw(TextureRegion texture, Params object) {
        batch.draw(texture,(object.getX()- object.getWidht()/2)*scaleWidth/PPM+originX/PPM,(object.getY()- object.getHeight()/2)*scaleHeight/PPM+originY/PPM,
                object.getWidht()/2*scaleWidth/PPM,object.getHeight()/2*scaleHeight/PPM,(object.getWidht())*scaleWidth/PPM,(object.getHeight())*scaleHeight/PPM,1,1,object.getRotation());
    }
    public void draw(TextureRegion texture, Params object,float scale) {
        batch.draw(texture,(object.getX()- object.getWidht()*scale/2)*scaleWidth/PPM+originX/PPM,(object.getY()- object.getHeight()*scale/2)*scaleHeight/PPM+originY/PPM,
                object.getWidht()*scale/2*scaleWidth/PPM,object.getHeight()*scale/2*scaleHeight/PPM,object.getWidht()*scale*scaleWidth/PPM,object.getHeight()*scale*scaleHeight/PPM,1,1,object.getRotation());
    }
    public void dispose()
    {
        backgroundTexture.dispose();
    }
}
