package com.pao.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    static final float FRAME_LENGTH = 0.2f;
    static final int SIZE = 200;
    static final int width = 300;
    static final int height = 300;
    static Animation<TextureRegion> animation = null;
    float x, y;
    float stateTime;
    public boolean finished = false;
    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;
        stateTime = 0;
        if(animation == null) animation = new Animation<>(FRAME_LENGTH, TextureRegion.split(new Texture(Gdx.files.internal("explosion.png")), SIZE, SIZE)[0]);
    }

    public void update(float time) {
        stateTime += time;
        if(animation.isAnimationFinished(stateTime)) {
            finished = true;
        }
    }
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(stateTime), x - width/2, y - height/2, width, height);
    }
    public boolean isFinished() { return finished; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
