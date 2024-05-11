package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.pao.game.viewmodel.Color;

public class Textures {
    Map<Color, Texture> tanks;
    Texture bullet;
    public Textures(int n)
    {
        tanks = new HashMap<>();
        tanks.put(null,new Texture(Gdx.files.internal("tank_gray.png")));
        for(Color color : Color.getColorList(n)){
            if(color == Color.R) tanks.put(color,new Texture(Gdx.files.internal("tank_red.png")));
            if(color == Color.B) tanks.put(color,new Texture(Gdx.files.internal("tank_blue.png")));
            if(color == Color.G) tanks.put(color,new Texture(Gdx.files.internal("tank_green.png")));
            if(color == Color.Y) tanks.put(color,new Texture(Gdx.files.internal("tank_yellow.png")));
        }
        bullet = new Texture(Gdx.files.internal("bullet.png"));
    }
    public Texture getTankTexture(Color color)
    {
        return tanks.get(color);
    }
    public Texture getBulletTexture()
    {
        return bullet;
    }
    public void dispose() {
        Iterator itr = tanks.values().iterator();
        while(itr.hasNext()){
            ((Texture) itr.next()).dispose();
        }
        bullet.dispose();
    }
}
