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
import com.pao.game.Constants.ViewConstants;
import com.pao.game.viewmodel.GlobalStatistics;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

public class EndScreen implements Screen {
    final float BUTTON_WIDTH = ViewConstants.getIntConstant("EndScreen.Button.Width");
    final float BUTTON_HEIGHT = ViewConstants.getIntConstant("EndScreen.Button.Height");
    final float START_BUTTON_Y = ViewConstants.getIntConstant("EndScreen.Start.Button.Y");
    final float EXIT_BUTTON_Y = ViewConstants.getIntConstant("EndScreen.Exit.Button.Y");
    final float STATISTICS_WIDTH = ViewConstants.getIntConstant("EndScreen.Statistics.Width");
    final float MEDAL_WIDTH = ViewConstants.getIntConstant("EndScreen.Medal.Width");
    final float MEDAL_HEIGHT = ViewConstants.getIntConstant("EndScreen.Medal.Height");
    final float MEDAL_Y = ViewConstants.getFloatConstant("EndScreen.Medal.Y");
    final int STATISTICS_FONT_SIZE = ViewConstants.getIntConstant("EndScreen.Statistics.Font.Size");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("EndScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("EndScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("EndScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("EndScreen.BackGround.A");
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
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT, new Color(
                BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A));
        painter.addFont("Player1", STATISTICS_FONT_SIZE, Color.YELLOW);
        painter.addFont("Player2", STATISTICS_FONT_SIZE, Color.GREEN);
        painter.addFont("Player3", STATISTICS_FONT_SIZE, Color.BLUE);
        painter.addFont("Player4", STATISTICS_FONT_SIZE, Color.RED);
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

        float x1 = -STATISTICS_WIDTH * (EditSettings.getNumberOfPlayers() - 1) / 2.0f;
        for(ModelPlayer player : GlobalStatistics.getPlayers()) {
            if(globalStatistics.getWinners().contains(player)) {
                painter.drawTexture(new TextureRegion(Textures.getTexture("medal")), Drop.WIDTH/2+x1, MEDAL_Y, MEDAL_WIDTH, MEDAL_HEIGHT, 0);
            }
            displayPlayerStatistics(Drop.WIDTH / 2 + x1, 800, player);
            x1 += STATISTICS_WIDTH;
        }

        painter.drawAlternatingTexture(Textures.getTexture("start.Button.Active"), Textures.getTexture("start.Button.Inactive"), startButton, touchPoint);
        if(startButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new GameScreen(game));

        painter.drawAlternatingTexture(Textures.getTexture("exit.Button.Active"), Textures.getTexture("exit.Button.Inactive"), exitButton, touchPoint);
        if(exitButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new MainMenuScreen(game));

        game.batch.end();
    }
    public void displayPlayerStatistics(float x, float y, ModelPlayer color) {
        String player = color.toString();
        painter.drowWriting(player, "#plates: " + globalStatistics.getNumberOfPlates(color), x, y);
        painter.drowWriting(player, "#kills: " + globalStatistics.getKillNumber(color), x, y - STATISTICS_FONT_SIZE);
        painter.drowWriting(player, "#deads: " + globalStatistics.getDeadNumber(color), x, y - 2* STATISTICS_FONT_SIZE);
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
