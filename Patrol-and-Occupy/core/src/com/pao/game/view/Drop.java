package com.pao.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

public class Drop extends Game {
    public SpriteBatch batch;
    //public BitmapFont font;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1054;
    GameScreen tmp;
    public void create() {
        //Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        //System.out.println(Gdx.graphics.getWidth()+" "+Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        //font = new BitmapFont();
//        tmp = new GameScreen(this,n,VM);
//        this.setScreen(tmp);
        this.setScreen(new MainMenuScreen(this, new EditSettings()));
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