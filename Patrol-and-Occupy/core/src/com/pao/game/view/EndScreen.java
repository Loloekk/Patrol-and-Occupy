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
    static final float BUTTON_WIDTH = 400;
    static final float BUTTON_HEIGHT = 200;
    static final float START_BUTTON_Y = 400;
    static final float EXIT_BUTTON_Y = 200;
    static final float STATISTICS_WIDTH = 200;
    Drop game;
    GlobalStatistics globalStatistics;
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle startButton;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    BitmapFont font;
    Textures textures;

    public EndScreen(Drop game, GlobalStatistics globalStatistics) {
        this.game = game;
        this.globalStatistics = globalStatistics;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        textures = new Textures(1);
        startButton = new Rectangle(Drop.WIDTH/2- BUTTON_WIDTH/2, START_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
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

        if(globalStatistics.getWinner() == ModelPlayer.Player1) font.setColor(Color.YELLOW);
        if(globalStatistics.getWinner() == ModelPlayer.Player2) font.setColor(Color.GREEN);
        if(globalStatistics.getWinner() == ModelPlayer.Player3) font.setColor(Color.BLUE);
        if(globalStatistics.getWinner() == ModelPlayer.Player4) font.setColor(Color.RED);
        font.draw(game.batch, "WINNER", Drop.WIDTH/2, 900);

        float x1 = -STATISTICS_WIDTH * EditSettings.getNumberOfPlayers() / 2;
        for(ModelPlayer color : GlobalStatistics.getPlayers()) {
            displayPlayerStatistics(Drop.WIDTH / 2 + x1, 800, color);
            x1 += STATISTICS_WIDTH;
        }

        if(startButton.contains(touchPoint.x, touchPoint.y)) {
            painter.drawTexture(new TextureRegion(textures.getStartButtonActive()), Drop.WIDTH / 2, START_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(new GameScreen(game));
            }
        }
        else {
            painter.drawTexture(new TextureRegion(textures.getStartButtonInactive()), Drop.WIDTH / 2, START_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }

        if(exitButton.contains(touchPoint.x, touchPoint.y)) {
            painter.drawTexture(new TextureRegion(textures.getExitButtonActive()), Drop.WIDTH / 2, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
            if(Gdx.input.justTouched()) {
                //this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else {
            painter.drawTexture(new TextureRegion(textures.getExitButtonInactive()), Drop.WIDTH / 2, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }
        game.batch.end();
    }

    public void displayPlayerStatistics(float x, float y, ModelPlayer color) {
        if(color == ModelPlayer.Player1) font.setColor(Color.YELLOW);
        if(color == ModelPlayer.Player2) font.setColor(Color.GREEN);
        if(color == ModelPlayer.Player3) font.setColor(Color.BLUE);
        if(color == ModelPlayer.Player4) font.setColor(Color.RED);
        font.draw(game.batch, "#plates: " + globalStatistics.getNumberOfPlates(color), x, y);
        font.draw(game.batch, "#kills: " + globalStatistics.getKillNumber(color), x, y - 20);
        font.draw(game.batch, "#deads: " + globalStatistics.getDeadNumber(color), x, y - 40);
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
