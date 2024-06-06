package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations {
    Texture dynamiteExplosion;
    Animation<TextureRegion> dynamiteExplosionAnimation;
    Texture bulletShoot;
    Animation<TextureRegion> bulletShootAnimation;
    Texture bulletExplosion;
    Animation<TextureRegion> bulletExplosionAnimation;

    public Animations() {
        dynamiteExplosion = new Texture(Gdx.files.internal("dynamiteExplosion.png"));
        dynamiteExplosionAnimation = loadAnimation(dynamiteExplosion, 200, 200, 0.1f);
        bulletShoot = new Texture(Gdx.files.internal("bulletShoot.png"));
        bulletShootAnimation = loadAnimation(bulletShoot, 150, 112, 0.05f);
        bulletExplosion = new Texture(Gdx.files.internal("bulletExplosion.png"));
        bulletExplosionAnimation = loadAnimation(bulletExplosion, 192, 192, 0.05f);
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
    public Animation<TextureRegion> getDynamiteExplosionAnimation() { return dynamiteExplosionAnimation; }
    public Animation<TextureRegion> getBulletShootAnimation() { return bulletShootAnimation; }
    public Animation<TextureRegion> getBulletExplosionAnimation() { return bulletExplosionAnimation; }
}
