package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.constants.ViewConstants;

public class MainMenuScreen implements Screen {
    static final float BUTTON_WIDTH = ViewConstants.getFloatConstant("MainMenuScreen.Button.Width");
    static final float BUTTON_HEIGHT = ViewConstants.getFloatConstant("MainMenuScreen.Button.Height");
    static final float PLAY_BUTTON_Y = ViewConstants.getFloatConstant("MainMenuScreen.Play.Button.Y");
    static final float SETTINGS_BUTTON_Y = ViewConstants.getFloatConstant("MainMenuScreen.Settings.Button.Y");
    static final float EXIT_BUTTON_Y = ViewConstants.getFloatConstant("MainMenuScreen.Exit.Button.Y");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("MainMenuScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("MainMenuScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("MainMenuScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("MainMenuScreen.BackGround.A");
    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle playButton;
    Rectangle settingsButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    public MainMenuScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        playButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, SETTINGS_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A));
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        painter.drawAlternatingTexture(Textures.getTexture("play.Button.Active"), Textures.getTexture("play.Button.Inactive"), playButton, touchPoint);
        if(playButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new PlayerNumberScreen(game));

        painter.drawAlternatingTexture(Textures.getTexture("settings.Button.Active"), Textures.getTexture("settings.Button.Inactive"), settingsButton, touchPoint);
        if(settingsButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new SettingsScreen(game));

        painter.drawAlternatingTexture(Textures.getTexture("exit.Button.Active"), Textures.getTexture("exit.Button.Inactive"), exitButton, touchPoint);
        if(exitButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            Gdx.app.exit();
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
