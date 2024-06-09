package com.pao.game.view.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.communication.Descriptions.ObjectDescription;

public class DrawingObject {
    TextureRegion texture;
    ObjectDescription desc;
    Integer priority;
    public DrawingObject(){
        texture = null;
        desc = null;
        priority = 0;
    }
    public DrawingObject setTexture(TextureRegion texture){
        this.texture = texture;
        return this;
    }
    public DrawingObject setDescription(ObjectDescription desc){
        this.desc = desc;
        return this;
    }
    public DrawingObject setPriority(Integer priority){
        this.priority = priority;
        return this;
    }
    public TextureRegion getTexture() { return texture; }
    public ObjectDescription getDescription() {return desc; }
    public Integer getPriority() { return priority; }


}
