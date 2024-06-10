package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import com.pao.game.communication.ConfigLoader;
import com.pao.game.model.ModelPlayer;

public class Textures {
    static Map<String,Texture> map = new HashMap<>();
    static ConfigLoader configLoader = new ConfigLoader("assets/textures.properties");
    public static Texture getTexture(String name)
    {
        if(!map.containsKey(name)) {
            String path = configLoader.getProperty(name);
            if (path == null) return null;
            map.put(name, new Texture(Gdx.files.internal(path)));
        }
        return map.get(name);
    }
    public static Texture getTexture(String name, ModelPlayer color)
    {
        if(!map.containsKey(name+"."+color)) {
            String path = configLoader.getProperty(name + "." + color);
            if (path == null) return null;
            map.put(name + "." + color, new Texture(Gdx.files.internal(path)));
        }
        return map.get(name+"."+color);
    }
    public static void dispose() {
        for (Texture texture : map.values())
            texture.dispose();
    }
}