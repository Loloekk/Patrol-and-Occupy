package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;
import com.pao.game.model.ModelPlayer;

public class Textures {
    Map<ModelPlayer, Texture> tanks;
    Texture bullet;
    Texture obstacle;
    Map<ModelPlayer, Texture> plates;
    Texture dynamite;
    public Textures(int n)
    {
        tanks = new HashMap<>();
        tanks.put(null,new Texture(Gdx.files.internal("tank_gray.png")));
        for(ModelPlayer color : ModelPlayer.getColorList(n)){
            if(color == ModelPlayer.Player4) tanks.put(color,new Texture(Gdx.files.internal("tank_red.png")));
            if(color == ModelPlayer.Player3) tanks.put(color,new Texture(Gdx.files.internal("tank_blue.png")));
            if(color == ModelPlayer.Player2) tanks.put(color,new Texture(Gdx.files.internal("tank_green.png")));
            if(color == ModelPlayer.Player1) tanks.put(color,new Texture(Gdx.files.internal("tank_yellow.png")));
        }
        bullet = new Texture(Gdx.files.internal("bullet.png"));
        obstacle = new Texture(Gdx.files.internal("rock.png"));
        plates = new HashMap<>();
        plates.put(null, new Texture(Gdx.files.internal("plate_gray.png")));
        for(ModelPlayer color : ModelPlayer.getColorList(n)){
            if(color == ModelPlayer.Player4) plates.put(color,new Texture(Gdx.files.internal("plate_red.png")));
            if(color == ModelPlayer.Player3) plates.put(color,new Texture(Gdx.files.internal("plate_blue.png")));
            if(color == ModelPlayer.Player2) plates.put(color,new Texture(Gdx.files.internal("plate_green.png")));
            if(color == ModelPlayer.Player1) plates.put(color,new Texture(Gdx.files.internal("plate_yellow.png")));
        }
        dynamite = new Texture(Gdx.files.internal("dynamite.jpeg"));
    }
    public Texture getTankTexture(ModelPlayer color)
    {
        return tanks.get(color);
    }
    public Texture getBulletTexture() { return bullet; }
    public Texture getObstacleTexture() { return obstacle; }
    public Texture getPlateTexture(ModelPlayer color) { return plates.get(color); }
    public Texture getDynamiteTexture() { return dynamite; }
    public void dispose() {
        for (Texture texture : tanks.values())
            texture.dispose();
        for (Texture texture : plates.values())
            texture.dispose();
        bullet.dispose();
        obstacle.dispose();
        dynamite.dispose();
    }
}