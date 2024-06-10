package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.Constants.ConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class Animations {
    static Map<String,Animation<TextureRegion>> mapAnimatioons = new HashMap<>();
    static Map<String,Texture> mapTextures = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/textures.properties");



    private static Animation<TextureRegion> loadAnimation(Texture texture, int width, int height, float frameDuration) {
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
    public static TextureRegion getFrame(String name, float time)
    {
        if(!mapAnimatioons.containsKey(name)) {
            mapTextures.put(name,new Texture(Gdx.files.internal(configLoader.getProperty(name))));
            mapAnimatioons.put(name, loadAnimation(mapTextures.get(name), configLoader.getIntProperty(name + ".Width"), configLoader.getIntProperty(name + ".Height"), configLoader.getFloatProperty(name + ".frameDuration")));
        }
        return mapAnimatioons.get(name).getKeyFrame(time);
    }
    public static void dispose() {
        for(Texture texture : mapTextures.values()){
            texture.dispose();
        }
    }
}
