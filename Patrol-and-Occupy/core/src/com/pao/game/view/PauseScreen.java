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
import com.pao.game.view.GameScreen.GameScreen;

public class PauseScreen implements Screen {
    static final float BUTTON_WIDTH = 400;
    static final float BUTTON_HEIGHT = 200;
    static final float RESUME_BUTTON_Y = 600;
    static final float RESTART_BUTTON_Y = 400;
    static final float EXIT_BUTTON_Y = 200;
    Drop game;
    OrthographicCamera camera;
    GameScreen gameScreen;
    Viewport viewport;
    Texture resumeButtonActive;
    Texture resumeButtonInactive;
    Texture restartButtonActive;
    Texture restartButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Rectangle resumeButton;
    Rectangle restartButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    BitmapFont font;

    public PauseScreen(Drop game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        resumeButtonActive = new Texture(Gdx.files.internal("resumeActive.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("resumeInactive.png"));
        restartButtonActive = new Texture(Gdx.files.internal("restartActive.png"));
        restartButtonInactive = new Texture(Gdx.files.internal("restartInactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitActive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitInactive.png"));
        resumeButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, RESUME_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, RESTART_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground(1f);
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(resumeButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(resumeButtonActive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, RESUME_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(gameScreen);
            }
        }
        else {
            game.batch.draw(resumeButtonInactive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, RESUME_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if(restartButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(restartButtonActive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, RESTART_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(new GameScreen(game));
            }
        }
        else {
            game.batch.draw(restartButtonInactive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, RESTART_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if(exitButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(exitButtonActive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else {
            game.batch.draw(exitButtonInactive, Drop.WIDTH / 2 - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
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
