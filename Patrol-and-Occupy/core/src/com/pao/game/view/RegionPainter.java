package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pao.game.communication.Descriptions.ObjectDescription;

import java.util.HashMap;
import java.util.Map;

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
    Map<String,BitmapFont> fonts;
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
        fonts = new HashMap<>();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = com.badlogic.gdx.graphics.Color.BLACK;
        fonts.put("default",generator.generateFont(parameter));
        generator.dispose();
    }
    public void addFont(String name,int size, Color color)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        fonts.put(name,generator.generateFont(parameter));
        generator.dispose();
    }
    public void fillBackground()
    {
        batch.draw(new TextureRegion(backgroundTexture),originX,originY,width,height);
    }
    public void drowWriting(String fontName,String text,float x, float y)
    {
        if(!fonts.containsKey(fontName)){fontName = "default";}
        GlyphLayout layout = new GlyphLayout();
        layout.setText(fonts.get(fontName),text);
        float textWidth = layout.width;
        float textHeight = layout.height;
        fonts.get(fontName).draw(batch,text,(x-textWidth/2)*scaleHeight+originX,(y+textHeight/2)*scaleWidth+originY);
    }
    public void drawTexture(TextureRegion texture, float x, float y, float W, float H,float R) {
        batch.draw(texture,(x-W/2)*scaleHeight+originX,(y-H/2)*scaleWidth+originY,
                W/2*scaleHeight,H/2*scaleWidth,W*scaleHeight,H*scaleWidth,1,1,R);
    }
    public void drawTexture(TextureRegion texture, ObjectDescription object) {
        batch.draw(texture,(object.getX()- object.getWidth()/2)*scaleWidth+originX,(object.getY()- object.getHeight()/2)*scaleHeight+originY,
                object.getWidth()/2*scaleWidth,object.getHeight()/2*scaleHeight,(object.getWidth())*scaleWidth,(object.getHeight())*scaleHeight,1,1,object.getRotation());
    }
    public void drawTexture(TextureRegion texture, ObjectDescription object,float scale) {
        batch.draw(texture,(object.getX()- object.getWidth()*scale/2)*scaleWidth+originX,(object.getY()- object.getHeight()*scale/2)*scaleHeight+originY,
                object.getWidth()*scale/2*scaleWidth,object.getHeight()*scale/2*scaleHeight,object.getWidth()*scale*scaleWidth,object.getHeight()*scale*scaleHeight,1,1,object.getRotation());
    }
    public void drawTexture(Texture texture, float x, float y, float W, float H,float R) {
        batch.draw(new TextureRegion(texture),(x-W/2)*scaleHeight+originX,(y-H/2)*scaleWidth+originY,
                W/2*scaleHeight,H/2*scaleWidth,W*scaleHeight,H*scaleWidth,1,1,R);
    }
    public void drawTexture(Texture texture, ObjectDescription object) {
        batch.draw(new TextureRegion(texture),(object.getX()- object.getWidth()/2)*scaleWidth+originX,(object.getY()- object.getHeight()/2)*scaleHeight+originY,
                object.getWidth()/2*scaleWidth,object.getHeight()/2*scaleHeight,(object.getWidth())*scaleWidth,(object.getHeight())*scaleHeight,1,1,object.getRotation());
    }
    public void drawTexture(Texture texture, ObjectDescription object,float scale) {
        batch.draw(new TextureRegion(texture),(object.getX()- object.getWidth()*scale/2)*scaleWidth+originX,(object.getY()- object.getHeight()*scale/2)*scaleHeight+originY,
                object.getWidth()*scale/2*scaleWidth,object.getHeight()*scale/2*scaleHeight,object.getWidth()*scale*scaleWidth,object.getHeight()*scale*scaleHeight,1,1,object.getRotation());
    }
    public void drawAlternatingTexture(Texture texture1, Texture texture2, Rectangle rectangle, Vector3 touchPoint) {
        if(rectangle.contains(touchPoint.x, touchPoint.y)) {
            batch.draw(new TextureRegion(texture1), rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        }
        else {
            batch.draw(new TextureRegion(texture2), rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        }
    }
    public void dispose()
    {
        backgroundTexture.dispose();
        for(BitmapFont font : fonts.values()){
            font.dispose();
        }
    }
}
