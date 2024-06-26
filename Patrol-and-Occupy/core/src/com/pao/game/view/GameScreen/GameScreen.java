package com.pao.game.view.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.pao.game.view.*;
import com.pao.game.view.GameScreen.Drawing.DrawingBoard;
import com.pao.game.view.GameScreen.Updating.UpdatingBoard;
import com.pao.game.viewmodel.*;
import com.pao.game.viewmodel.EditSettings;

public class GameScreen implements Screen {
    final Drop game;
    ViewModel VM;
    DrawingBoard drawingBoard;
    UpdatingBoard updatingBoard;

    public GameScreen(final Drop game){
        this.game=game;
        this.VM = new ViewModel();
        drawingBoard = new DrawingBoard(game,VM);
        updatingBoard = new UpdatingBoard(VM,EditSettings.getNumberOfPlayers());
    }
    private void screenChange()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(game, this));
        }
        if(VM.getRemainingTime() == 0f) {
            game.setScreen(new EndScreen(game,VM.getStatistics()));
        }
    }
    @Override
    public void render(float delta){
        updatingBoard.update(delta);
        drawingBoard.draw();
        screenChange();
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