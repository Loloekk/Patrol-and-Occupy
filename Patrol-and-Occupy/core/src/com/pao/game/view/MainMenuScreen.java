package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.view.GameScreen.GameScreen;

public class MainMenuScreen implements Screen {
    static final float BUTTON_WIDTH = 300 ;
    static final float BUTTON_HEIGHT = 200 ;
    static final float PLAY_BUTTON_Y = 550 ;
    static final float SETTINGS_BUTTON_Y = 350 ;
    static final float EXIT_BUTTON_Y = 150 ;
    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle playButton;
    Rectangle settingsButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    Textures textures;
    public MainMenuScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        textures = new Textures(1);
        playButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, SETTINGS_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.4f,0.4f,0.9f,1));
    }
    @Override
    public void render(float delta) {
        //game.setScreen(new GameScreen(game));
        ScreenUtils.clear(0f, 0f, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(playButton.contains(touchPoint.x, touchPoint.y)) {
            painter.drawTexture(new TextureRegion(textures.getPlayButtonActive()), Drop.WIDTH / 2, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(new GameScreen(game));
            }
        }
        else {
            painter.drawTexture(new TextureRegion(textures.getPlayButtonInactive()), Drop.WIDTH / 2, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }

        if(settingsButton.contains(touchPoint.x, touchPoint.y)) {
            painter.drawTexture(new TextureRegion(textures.getSettingsButtonActive()), Drop.WIDTH / 2, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
            if(Gdx.input.justTouched()) {
                game.setScreen(new SettingsScreen(game));
            }
        }
        else {
            painter.drawTexture(new TextureRegion(textures.getSettingsButtonInactive()), Drop.WIDTH / 2, SETTINGS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }

        if(exitButton.contains(touchPoint.x, touchPoint.y)) {
            painter.drawTexture(new TextureRegion(textures.getExitButtonActive()), Drop.WIDTH / 2, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
            if(Gdx.input.justTouched()) {
                Gdx.app.exit();
            }
        }
        else {
            painter.drawTexture(new TextureRegion(textures.getExitButtonInactive()), Drop.WIDTH / 2, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }
        game.batch.end();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) { viewport.update(width, height); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }
}
