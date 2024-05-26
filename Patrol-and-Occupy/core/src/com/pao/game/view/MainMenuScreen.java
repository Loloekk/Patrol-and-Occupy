package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

public class MainMenuScreen implements Screen {
    static final float BUTTON_WIDTH = 300 ;
    static final float BUTTON_HEIGHT = 200 ;
    static final float PLAY_BUTTON_Y = 550 ;
    static final float SETTINGS_BUTTON_Y = 350 ;
    static final float EXIT_BUTTON_Y = 150 ;
    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture settingsButtonActive;
    Texture settingsButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Rectangle playButton;
    Rectangle settingsButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    EditSettings ES;

    public MainMenuScreen(Drop game, EditSettings ES) {
        this.game = game;
        this.ES = ES;
        //ES = new EditSettings();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        playButtonActive = new Texture(Gdx.files.internal("playActive.png"));
        playButtonInactive = new Texture(Gdx.files.internal("playInactive.png"));
        settingsButtonActive = new Texture(Gdx.files.internal("settingsActive.png"));
        settingsButtonInactive = new Texture(Gdx.files.internal("settingsInactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitActive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitInactive.png"));
        playButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, SETTINGS_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.9f,0.4f,0.4f,1));
    }
    @Override
    public void render(float delta) {
        //Gdx.gl.glClear();
        ScreenUtils.clear(0f, 0f, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painter.fillBackground(1f);
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(playButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(playButtonActive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                //this.dispose();
                game.setScreen(new GameScreen(game,ES));
            }
        }
        else {
            game.batch.draw(playButtonInactive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        // RegionPainter--------------------------------------------------------------
        if(settingsButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(settingsButtonActive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, SETTINGS_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                game.setScreen(new SettingsScreen(game, ES));     //---------------------------------------------------
            }
        }
        else {
            game.batch.draw(settingsButtonInactive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, SETTINGS_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if(exitButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(exitButtonActive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else {
            game.batch.draw(exitButtonInactive, Drop.WIDTH / 2  - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
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
