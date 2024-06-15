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
import com.pao.game.constants.ViewConstants;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.control;
import static com.pao.game.communication.Options.numberOfPlayers;

public class PlayerNumberScreen implements Screen {
    final float BUTTON_WIDTH = ViewConstants.getFloatConstant("PlayerNumberScreen.Button.Width");
    final float BUTTON_HEIGHT = ViewConstants.getFloatConstant("PlayerNumberScreen.Button.Height") ;
    final float BACK_BUTTON_Y = ViewConstants.getFloatConstant("PlayerNumberScreen.Back.Button.Y") ;
    final float PLAYER_BUTTON_WIDTH = ViewConstants.getFloatConstant("PlayerNumberScreen.Player.Button.Width");
    final float PLAYER_BUTTON_HEIGHT = ViewConstants.getFloatConstant("PlayerNumberScreen.Player.Button.Height");
    final float PLAYER_BUTTON_Y = ViewConstants.getFloatConstant("PlayerNumberScreen.Player.Button.Y");
    final float PLAYER_BUTTON_SPACE = ViewConstants.getFloatConstant("PlayerNumberScreen.Player.Button.Space");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("PlayerNumberScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("PlayerNumberScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("PlayerNumberScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("PlayerNumberScreen.BackGround.A");

    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle backButton;
    Ellipse twoPlayers;
    Ellipse threePlayers;
    Ellipse fourPlayers;
    Vector3 touchPoint;
    RegionPainter painter;
    public PlayerNumberScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);

        backButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, BACK_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        twoPlayers = new Ellipse(Drop.WIDTH/2 - PLAYER_BUTTON_SPACE, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);
        threePlayers = new Ellipse(Drop.WIDTH/2, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);
        fourPlayers = new Ellipse(Drop.WIDTH/2 + PLAYER_BUTTON_SPACE, PLAYER_BUTTON_Y, PLAYER_BUTTON_WIDTH/2, PLAYER_BUTTON_HEIGHT/2);

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

        painter.drawTexture(new TextureRegion(Textures.getTexture("two.Players")), twoPlayers.x, twoPlayers.y, twoPlayers.width, twoPlayers.height, 0);
        painter.drawTexture(new TextureRegion(Textures.getTexture("three.Players")), threePlayers.x, threePlayers.y, threePlayers.width, threePlayers.height, 0);
        painter.drawTexture(new TextureRegion(Textures.getTexture("four.Players")), fourPlayers.x, fourPlayers.y, fourPlayers.width, fourPlayers.height, 0);

        if(twoPlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 2);
            EditSettings.setOption(control, 0);
            game.setScreen(new MapScreen(game));
        }
        if(threePlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 3);
            EditSettings.setOption(control, 1);
            game.setScreen(new MapScreen(game));
        }
        if(fourPlayers.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) {
            EditSettings.setOption(numberOfPlayers, 4);
            EditSettings.setOption(control, 1);
            game.setScreen(new MapScreen(game));
        }

        painter.drawAlternatingTexture(Textures.getTexture("back.Button.Active"), Textures.getTexture("back.Button.Inactive"), backButton, touchPoint);
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
