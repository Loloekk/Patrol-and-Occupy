package com.pao.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pao.game.viewmodel.ViewModel;

public class Drop extends Game {
    public SpriteBatch batch;
    //public BitmapFont font;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1054;
    GameScreen tmp;
    public void create() {
        //Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        System.out.println(Gdx.graphics.getWidth()+" "+Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        int n = 2;
        //font = new BitmapFont();
        ViewModel VM = new ViewModel(1920,1080,n);
        Thread thread = new Thread(VM);
        thread.setDaemon(true);
        thread.start();
//        tmp = new GameScreen(this,n,VM);
//        this.setScreen(tmp);
        this.setScreen(new MainMenuScreen(this, n, VM));
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