package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.Constants.ViewConstants;
import com.pao.game.view.GameScreen.GameScreen;

public class PauseScreen implements Screen {
    static final float BUTTON_WIDTH = ViewConstants.getFloatConstant("PauseScreen.Button.Width");
    static final float BUTTON_HEIGHT = ViewConstants.getFloatConstant("PauseScreen.Button.Height");
    static final float RESUME_BUTTON_Y = ViewConstants.getFloatConstant("PauseScreen.Resume.Button.Y");
    static final float RESTART_BUTTON_Y = ViewConstants.getFloatConstant("PauseScreen.Restart.Button.Y");
    static final float EXIT_BUTTON_Y = ViewConstants.getFloatConstant("PauseScreen.Exit.Button.Y");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("PauseScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("PauseScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("PauseScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("PauseScreen.BackGround.A");
    Drop game;
    OrthographicCamera camera;
    GameScreen gameScreen;
    Viewport viewport;
    Rectangle resumeButton;
    Rectangle restartButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;

    public PauseScreen(Drop game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        resumeButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, RESUME_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, RESTART_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        painter.drawAlternatingTexture(Textures.getTexture("resume.Button.Active"), Textures.getTexture("resume.Button.Inactive"), resumeButton, touchPoint);
        if(resumeButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(gameScreen);

        painter.drawAlternatingTexture(Textures.getTexture("restart.Button.Active"), Textures.getTexture("restart.Button.Inactive"), restartButton, touchPoint);
        if(restartButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new GameScreen(game));

        painter.drawAlternatingTexture(Textures.getTexture("exit.Button.Active"), Textures.getTexture("exit.Button.Inactive"), exitButton, touchPoint);
        if(exitButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new MainMenuScreen(game));

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
