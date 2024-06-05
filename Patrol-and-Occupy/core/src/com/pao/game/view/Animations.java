package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations {
    Texture explosion;
    Animation<TextureRegion> explosionAnimation;

    public Animations() {
        explosion = new Texture(Gdx.files.internal("explosion.png"));
        explosionAnimation = loadAnimation(explosion, 200, 200, 0.1f);
    }

    private Animation<TextureRegion> loadAnimation(Texture texture, int width, int height, float frameDuration) {
        TextureRegion[][] tmpFrames = TextureRegion.split(texture, width, height);
        TextureRegion[] animationFrames = new TextureRegion[tmpFrames.length * tmpFrames[0].length];
        int index = 0;
        for (TextureRegion[] tmpFrame : tmpFrames) {
            for (TextureRegion textureRegion : tmpFrame) {
                animationFrames[index++] = textureRegion;
            }
        }
        return new Animation<>(frameDuration, animationFrames);
    }
    public Animation<TextureRegion> getExplosionAnimation() { return explosionAnimation; }
}
