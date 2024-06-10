package com.pao.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pao.game.view.GameScreen.GameScreen;

public class Drop extends Game {
    public SpriteBatch batch;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1054;
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        Textures.dispose();
        Animations.dispose();
        //font.dispose();
    }
}