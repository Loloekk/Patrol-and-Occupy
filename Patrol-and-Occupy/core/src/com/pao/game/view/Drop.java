package com.pao.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game {
    public SpriteBatch batch;
    //public BitmapFont font;
    GameScreen tmp;
    public void create() {
        batch = new SpriteBatch();
        //font = new BitmapFont();
        tmp = new GameScreen(this,2);
        this.setScreen(tmp);
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        tmp.dispose();
        //font.dispose();
    }
}