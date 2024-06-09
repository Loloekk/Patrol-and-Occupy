package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.communication.Options;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.control;
import static com.pao.game.communication.Options.numberOfPlayers;

public class MapScreen implements Screen {
    static final float BUTTON_WIDTH = 300 ;
    static final float BUTTON_HEIGHT = 200 ;
    static final float PLAY_BUTTON_Y = 450 ;
    static final float BACK_BUTTON_Y = 250 ;
    static final float PLAYER_BUTTON_WIDTH = 600;
    static final float PLAYER_BUTTON_HEIGHT = 500;
    static final float PLAYER_BUTTON_Y = 700;

    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle playButton;
    Rectangle backButton;
    Ellipse twoPlayers;
    Ellipse threePlayers;
    Ellipse fourPlayers;
    Vector3 touchPoint;
    RegionPainter painter;
    public MapScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);

        playButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, PLAY_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, BACK_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        twoPlayers = new Ellipse(Drop.WIDTH/2 - 400, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);
        threePlayers = new Ellipse(Drop.WIDTH/2, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);
        fourPlayers = new Ellipse(Drop.WIDTH/2 + 400, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);

        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.4f,0.4f,0.9f,1));
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.4f,0.4f,0.9f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        painter.drawTexture(new TextureRegion(Textures.getTwoPlayers()), twoPlayers.x, twoPlayers.y, twoPlayers.width, twoPlayers.height, 0);
        painter.drawTexture(new TextureRegion(Textures.getThreePlayers()), threePlayers.x, threePlayers.y, threePlayers.width, threePlayers.height, 0);
        painter.drawTexture(new TextureRegion(Textures.getFourPlayers()), fourPlayers.x, fourPlayers.y, fourPlayers.width, fourPlayers.height, 0);

        if(twoPlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 2);
            EditSettings.setOption(control, 0);
            game.setScreen(new GameScreen(game));
        }
        if(threePlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 3);
            EditSettings.setOption(control, 1);
            game.setScreen(new GameScreen(game));
        }
        if(fourPlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 4);
            EditSettings.setOption(control, 1);
            game.setScreen(new GameScreen(game));
        }

        painter.drawAlternatingTexture(Textures.getBackButtonActive(), Textures.getBackButtonInactive(), backButton, touchPoint);
        if(backButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new MainMenuScreen(game));

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
