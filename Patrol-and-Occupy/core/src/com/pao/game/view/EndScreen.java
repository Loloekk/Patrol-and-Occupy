package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.viewmodel.GlobalStatistics;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

public class EndScreen implements Screen {
    static final float BUTTON_WIDTH = 300;
    static final float BUTTON_HEIGHT = 200;
    static final float START_BUTTON_Y = 400;
    static final float EXIT_BUTTON_Y = 200;
    static final float STATISTICS_WIDTH = 400;
    Drop game;
    GlobalStatistics globalStatistics;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle startButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    public EndScreen(Drop game, GlobalStatistics globalStatistics) {
        this.game = game;
        this.globalStatistics = globalStatistics;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        startButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, START_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, EXIT_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));
        painter.addFont("WINNER", 50, Color.RED);
        painter.addFont("Player1", 70, Color.YELLOW);
        painter.addFont("Player2", 70, Color.GREEN);
        painter.addFont("Player3", 70, Color.BLUE);
        painter.addFont("Player4", 70, Color.RED);
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
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        displayResult(Drop.WIDTH/2, 900);

        float x1 = -STATISTICS_WIDTH * (EditSettings.getNumberOfPlayers() - 1) / 2.0f;
        for(ModelPlayer color : GlobalStatistics.getPlayers()) {
            displayPlayerStatistics(Drop.WIDTH / 2 + x1, 800, color);
            x1 += STATISTICS_WIDTH;
        }

        painter.drawAlternatingTexture(Textures.getStartButtonActive(), Textures.getStartButtonInactive(), startButton, touchPoint);
        if(startButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new GameScreen(game));

        painter.drawAlternatingTexture(Textures.getExitButtonActive(), Textures.getExitButtonInactive(), exitButton, touchPoint);
        if(exitButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new MainMenuScreen(game));

        game.batch.end();
    }
    public void displayResult(float x, float y) {
//        if(globalStatistics.getWinner() == ModelPlayer.Player1) font.setColor(Color.YELLOW);
//        if(globalStatistics.getWinner() == ModelPlayer.Player2) font.setColor(Color.GREEN);
//        if(globalStatistics.getWinner() == ModelPlayer.Player3) font.setColor(Color.BLUE);
//        if(globalStatistics.getWinner() == ModelPlayer.Player4) font.setColor(Color.RED);
//        painter.drowWriting("WINNER", "WINNER", x, y);
    }
    public void displayPlayerStatistics(float x, float y, ModelPlayer color) {
        String player = "";
        if(color == ModelPlayer.Player1) player = "Player1";
        if(color == ModelPlayer.Player2) player = "Player2";
        if(color == ModelPlayer.Player3) player = "Player3";
        if(color == ModelPlayer.Player4) player = "Player4";
        painter.drowWriting(player, "#plates: " + globalStatistics.getNumberOfPlates(color), x, y);
        painter.drowWriting(player, "#kills: " + globalStatistics.getKillNumber(color), x, y - 70);
        painter.drowWriting(player, "#deads: " + globalStatistics.getDeadNumber(color), x, y - 140);
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
