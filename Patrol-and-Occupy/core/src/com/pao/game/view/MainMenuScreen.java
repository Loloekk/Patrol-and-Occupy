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
import com.pao.game.viewmodel.ViewModel;

public class MainMenuScreen implements Screen {
    static final int BUTTON_WIDTH = 300;
    static final int BUTTON_HEIGHT = 200;
    static final int PLAY_BUTTON_Y = 550;
    static final int EXIT_BUTTON_Y = 300;
    Drop game;
    OrthographicCamera camera;
    int n;
    ViewModel VM;
    Viewport viewport;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Rectangle playButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    int width;
    int height;
    RegionPainter painter;
    public MainMenuScreen(Drop game, int n, ViewModel VM) {
        this.game = game;
        this.n = n;
        this.VM = VM;
        width = 1920;
        height = 1080;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        viewport = new ExtendViewport(width, height, camera);
        playButtonActive = new Texture(Gdx.files.internal("playActive.png"));
        playButtonInactive = new Texture(Gdx.files.internal("playInactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("exitActive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("exitInactive.png"));
        playButton = new Rectangle(width/2 - BUTTON_WIDTH/2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(width/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,width,height,width,height,new Color(0.9f,0.4f,0.4f,1));
    }
    @Override
    public void render(float delta) {
        //Gdx.gl.glClear();
        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(playButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(playButtonActive, width / 2 - BUTTON_WIDTH / 2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                //this.dispose();
                game.setScreen(new GameScreen(game, n, VM));
            }
        }
        else {
            game.batch.draw(playButtonInactive, width / 2 - BUTTON_WIDTH / 2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        }

        if(exitButton.contains(touchPoint.x, touchPoint.y)) {
            game.batch.draw(exitButtonActive, width / 2 - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else {
            game.batch.draw(exitButtonInactive, width / 2 - BUTTON_WIDTH / 2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
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
