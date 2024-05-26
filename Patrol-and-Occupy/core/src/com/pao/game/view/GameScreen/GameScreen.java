package com.pao.game.view.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.Communication.ColoredParams;
import com.pao.game.Communication.Move;
import com.pao.game.Communication.Params;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.*;
import com.pao.game.viewmodel.*;
import com.pao.game.viewmodel.EditSettings;

import java.util.ArrayList;
import java.util.List;



public class GameScreen implements Screen {
    final Drop game;
    ViewModel VM;

    EditSettings ES;
    DrawingBoard drawingBoard;
    UpdatingBoard updatingBoard;
    //Rectangle settingsButton;   // pozycja, wymiary
    //Vector3 touchPoint;

    public GameScreen(final Drop game,EditSettings ES){
        this.game=game;
        this.ES = ES;
        this.VM = new ViewModel(ES);
        drawingBoard = new DrawingBoard(game,VM,ES.getNumberOfPlayers());
        updatingBoard = new UpdatingBoard(VM,ES.getNumberOfPlayers());
    }
    private void screenChanege()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(game, ES, this));
        }
        if(VM.getRemainingTime() == 0f) {
            game.setScreen(new EndScreen(game, ES));
        }
    }
    @Override
    public void render(float delta){
        updatingBoard.update(delta);
        drawingBoard.draw();
        screenChanege();
    }
    public void resize(int width, int height) {
        drawingBoard.resize(width,height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

        drawingBoard.dispose();
    }


}