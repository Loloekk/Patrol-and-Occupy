package com.pao.game.communication.Descriptions;

public class ObjectDescription {
    float x;
    float y;
    float width;
    float height;
    float rotation;
    public ObjectDescription(){
        x=0;
        y=0;
        width=0;
        height=0;
        rotation=0;
    }
    public ObjectDescription(ObjectDescription desc){
        x=desc.getX();
        y=desc.getY();
        width=desc.getWidth();
        height=desc.getHeight();
        rotation=desc.getRotation();
    }
    public ObjectDescription setX(float x) { this.x=x; return this;}
    public ObjectDescription setY(float y) { this.y=y; return this;}
    public ObjectDescription setWidth(float width) { this.width=width; return this;}
    public ObjectDescription setHeight(float height) { this.height=height; return this;}
    public ObjectDescription setRotation(float rotation) { this.rotation = rotation; return this;}
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getRotation(){
        return rotation;
    }
}
