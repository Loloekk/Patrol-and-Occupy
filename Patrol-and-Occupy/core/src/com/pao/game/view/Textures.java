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
    Texture breakableObstacle;
    Map<ModelPlayer, Texture> plates;
    Map<ModelPlayer, Texture> magazines;
    Texture dynamite;
    Map<ModelPlayer, Texture> spawns;
    Texture startButtonActive;
    Texture startButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture settingsButtonActive;
    Texture settingsButtonInactive;
    public Textures(int n) {
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
        breakableObstacle = new Texture(Gdx.files.internal("rock_breakable.png"));
        plates = new HashMap<>();
        plates.put(null, new Texture(Gdx.files.internal("plate_gray.png")));
        for(ModelPlayer color : ModelPlayer.getColorList(n)){
            if(color == ModelPlayer.Player4) plates.put(color,new Texture(Gdx.files.internal("plate_red.png")));
            if(color == ModelPlayer.Player3) plates.put(color,new Texture(Gdx.files.internal("plate_blue.png")));
            if(color == ModelPlayer.Player2) plates.put(color,new Texture(Gdx.files.internal("plate_green.png")));
            if(color == ModelPlayer.Player1) plates.put(color,new Texture(Gdx.files.internal("plate_yellow.png")));
        }
        dynamite = new Texture(Gdx.files.internal("dynamite.jpeg"));
        spawns = new HashMap<>();
        for(ModelPlayer color : ModelPlayer.getColorList(n)){
            if(color == ModelPlayer.Player4) spawns.put(color,new Texture(Gdx.files.internal("spawn_red.png")));
            if(color == ModelPlayer.Player3) spawns.put(color,new Texture(Gdx.files.internal("spawn_blue.png")));
            if(color == ModelPlayer.Player2) spawns.put(color,new Texture(Gdx.files.internal("spawn_green.png")));
            if(color == ModelPlayer.Player1) spawns.put(color,new Texture(Gdx.files.internal("spawn_yellow.png")));
        }
        magazines = new HashMap<>();
        for(ModelPlayer color : ModelPlayer.getColorList(n)){
            if(color == ModelPlayer.Player4) magazines.put(color,new Texture(Gdx.files.internal("magazine_red.png")));
            if(color == ModelPlayer.Player3) magazines.put(color,new Texture(Gdx.files.internal("magazine_blue.png")));
            if(color == ModelPlayer.Player2) magazines.put(color,new Texture(Gdx.files.internal("magazine_green.png")));
            if(color == ModelPlayer.Player1) magazines.put(color,new Texture(Gdx.files.internal("magazine_yellow.png")));
        }
        startButtonActive = new Texture(Gdx.files.internal("startActive.png"));
        startButtonInactive = new Texture(Gdx.files.internal("startInactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitActive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitInactive.png"));
        playButtonActive = new Texture(Gdx.files.internal("playActive.png"));
        playButtonInactive = new Texture(Gdx.files.internal("playInactive.png"));
        settingsButtonActive = new Texture(Gdx.files.internal("settingsActive.png"));
        settingsButtonInactive = new Texture(Gdx.files.internal("settingsInactive.png"));
    }
    public Texture getTankTexture(ModelPlayer color)
    {
        return tanks.get(color);
    }
    public Texture getBulletTexture() { return bullet; }
    public Texture getObstacleTexture() { return obstacle; }
    public Texture getBreakableObstacleTexture() { return breakableObstacle; }
    public Texture getPlateTexture(ModelPlayer color) { return plates.get(color); }
    public Texture getMagazineTexture(ModelPlayer color) { return magazines.get(color); }
    public Texture getDynamiteTexture() { return dynamite; }
    public Texture getSpawnTexture(ModelPlayer color) { return spawns.get(color); }
    public Texture getStartButtonActive() { return startButtonActive; }
    public Texture getStartButtonInactive() { return startButtonInactive; }
    public Texture getExitButtonActive() { return exitButtonActive; }
    public Texture getExitButtonInactive() { return exitButtonInactive; }
    public Texture getPlayButtonActive() { return playButtonActive; }
    public Texture getPlayButtonInactive() { return playButtonInactive; }
    public Texture getSettingsButtonActive() { return settingsButtonActive; }
    public Texture getSettingsButtonInactive() { return settingsButtonInactive; }

    public void dispose() {
        for (Texture texture : tanks.values())
            texture.dispose();
        for (Texture texture : plates.values())
            texture.dispose();
        for (Texture texture : spawns.values())
            texture.dispose();
        bullet.dispose();
        obstacle.dispose();
        dynamite.dispose();
        breakableObstacle.dispose();
    }
}