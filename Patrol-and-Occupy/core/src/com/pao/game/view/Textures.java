package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.pao.game.viewmodel.MyColor;

public class Textures {
    Map<MyColor, Texture> tanks;
    Texture bullet;
    Texture obstacle;
    public Textures(int n)
    {
        tanks = new HashMap<>();
        tanks.put(null,new Texture(Gdx.files.internal("tank_gray.png")));
        for(MyColor color : MyColor.getColorList(n)){
            if(color == MyColor.R) tanks.put(color,new Texture(Gdx.files.internal("tank_red.png")));
            if(color == MyColor.B) tanks.put(color,new Texture(Gdx.files.internal("tank_blue.png")));
            if(color == MyColor.G) tanks.put(color,new Texture(Gdx.files.internal("tank_green.png")));
            if(color == MyColor.Y) tanks.put(color,new Texture(Gdx.files.internal("tank_yellow.png")));
        }
        bullet = new Texture(Gdx.files.internal("bullet.png"));
        obstacle = new Texture(Gdx.files.internal("rock.png"));
    }
    public Texture getTankTexture(MyColor color)
    {
        return tanks.get(color);
    }
    public Texture getBulletTexture()
    {
        return bullet;
    }
    public Texture getObstacleTexture() { return obstacle; }
    public void dispose() {
        Iterator itr = tanks.values().iterator();
        while(itr.hasNext()){
            ((Texture) itr.next()).dispose();
        }
        bullet.dispose();
    }
}