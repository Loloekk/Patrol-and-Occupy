package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;
import com.pao.game.model.ModelPlayer;
import org.w3c.dom.Text;

public class Textures {
    static Map<ModelPlayer, Texture> tanks;
    static Texture bullet;
    static Texture unbreakableObstacle;
    static Texture breakableObstacle;
    static Map<ModelPlayer, Texture> plates;
    static Map<ModelPlayer, Texture> magazines;
    static Texture dynamite;
    static Map<ModelPlayer, Texture> spawns;
    static Texture startButtonActive;
    static Texture startButtonInactive;
    static Texture exitButtonActive;
    static Texture exitButtonInactive;
    static Texture playButtonActive;
    static Texture playButtonInactive;
    static Texture settingsButtonActive;
    static Texture settingsButtonInactive;
    static Texture resumeButtonActive;
    static Texture resumeButtonInactive;
    static Texture restartButtonActive;
    static Texture restartButtonInactive;
    static Texture backButtonActive;
    static Texture backButtonInactive;
    static Texture twoPlayers;
    static Texture threePlayers;
    static Texture fourPlayers;
    static Texture medal;
    public static void load() {
        tanks = new HashMap<>();
        tanks.put(null,new Texture(Gdx.files.internal("tank_gray.png")));
        for(ModelPlayer color : ModelPlayer.getAllColorList()){
            if(color == ModelPlayer.Player4) tanks.put(color,new Texture(Gdx.files.internal("tank_red.png")));
            if(color == ModelPlayer.Player3) tanks.put(color,new Texture(Gdx.files.internal("tank_blue.png")));
            if(color == ModelPlayer.Player2) tanks.put(color,new Texture(Gdx.files.internal("tank_green.png")));
            if(color == ModelPlayer.Player1) tanks.put(color,new Texture(Gdx.files.internal("tank_yellow.png")));
        }
        bullet = new Texture(Gdx.files.internal("bullet.png"));
        unbreakableObstacle = new Texture(Gdx.files.internal("rock.png"));
        breakableObstacle = new Texture(Gdx.files.internal("rock_breakable.png"));
        plates = new HashMap<>();
        plates.put(null, new Texture(Gdx.files.internal("plate_gray.png")));
        for(ModelPlayer color : ModelPlayer.getAllColorList()){
            if(color == ModelPlayer.Player4) plates.put(color,new Texture(Gdx.files.internal("plate_red.png")));
            if(color == ModelPlayer.Player3) plates.put(color,new Texture(Gdx.files.internal("plate_blue.png")));
            if(color == ModelPlayer.Player2) plates.put(color,new Texture(Gdx.files.internal("plate_green.png")));
            if(color == ModelPlayer.Player1) plates.put(color,new Texture(Gdx.files.internal("plate_yellow.png")));
        }
        dynamite = new Texture(Gdx.files.internal("dynamite.jpeg"));
        spawns = new HashMap<>();
        for(ModelPlayer color : ModelPlayer.getAllColorList()){
            if(color == ModelPlayer.Player4) spawns.put(color,new Texture(Gdx.files.internal("spawn_red.png")));
            if(color == ModelPlayer.Player3) spawns.put(color,new Texture(Gdx.files.internal("spawn_blue.png")));
            if(color == ModelPlayer.Player2) spawns.put(color,new Texture(Gdx.files.internal("spawn_green.png")));
            if(color == ModelPlayer.Player1) spawns.put(color,new Texture(Gdx.files.internal("spawn_yellow.png")));
        }
        magazines = new HashMap<>();
        for(ModelPlayer color : ModelPlayer.getAllColorList()){
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
        resumeButtonActive = new Texture(Gdx.files.internal("resumeActive.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("resumeInactive.png"));
        restartButtonActive = new Texture(Gdx.files.internal("restartActive.png"));
        restartButtonInactive = new Texture(Gdx.files.internal("restartInactive.png"));
        backButtonActive = new Texture(Gdx.files.internal("backActive.png"));
        backButtonInactive = new Texture(Gdx.files.internal("backInactive.png"));
        twoPlayers = new Texture(Gdx.files.internal("twoPlayers.png"));
        threePlayers = new Texture(Gdx.files.internal("threePlayers.png"));
        fourPlayers = new Texture(Gdx.files.internal("fourPlayers.png"));
        medal = new Texture(Gdx.files.internal("medal.png"));
    }
    public static Texture getTankTexture(ModelPlayer color)
    {
        return tanks.get(color);
    }
    public static Texture getBulletTexture() { return bullet; }
    public static Texture getUnbreakableObstacleTexture() { return unbreakableObstacle; }
    public static Texture getBreakableObstacleTexture() { return breakableObstacle; }
    public static Texture getPlateTexture(ModelPlayer color) { return plates.get(color); }
    public static Texture getMagazineTexture(ModelPlayer color) { return magazines.get(color); }
    public static Texture getDynamiteTexture() { return dynamite; }
    public static Texture getSpawnTexture(ModelPlayer color) { return spawns.get(color); }
    public static Texture getStartButtonActive() { return startButtonActive; }
    public static Texture getStartButtonInactive() { return startButtonInactive; }
    public static Texture getExitButtonActive() { return exitButtonActive; }
    public static Texture getExitButtonInactive() { return exitButtonInactive; }
    public static Texture getPlayButtonActive() { return playButtonActive; }
    public static Texture getPlayButtonInactive() { return playButtonInactive; }
    public static Texture getSettingsButtonActive() { return settingsButtonActive; }
    public static Texture getSettingsButtonInactive() { return settingsButtonInactive; }
    public static Texture getResumeButtonActive() { return resumeButtonActive; }
    public static Texture getResumeButtonInactive() { return resumeButtonInactive; }
    public static Texture getRestartButtonActive() { return restartButtonActive; }
    public static Texture getRestartButtonInactive() { return restartButtonInactive; }
    public static Texture getBackButtonActive() { return backButtonActive; }
    public static Texture getBackButtonInactive() { return backButtonInactive; }
    public static Texture getTwoPlayers() { return twoPlayers; }
    public static Texture getThreePlayers() { return threePlayers; }
    public static Texture getFourPlayers() { return fourPlayers; }
    public static Texture getMedal() { return medal; }
    public static void dispose() {
        for (Texture texture : tanks.values())
            texture.dispose();
        for (Texture texture : plates.values())
            texture.dispose();
        for (Texture texture : spawns.values())
            texture.dispose();
        bullet.dispose();
        unbreakableObstacle.dispose();
        dynamite.dispose();
        breakableObstacle.dispose();
    }
}