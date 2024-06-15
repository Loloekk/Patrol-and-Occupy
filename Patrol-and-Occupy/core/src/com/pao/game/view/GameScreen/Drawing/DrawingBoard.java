package com.pao.game.view.GameScreen.Drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.constants.ViewConstants;
import com.pao.game.view.Drop;
import com.pao.game.view.GameScreen.Drawing.ObjectDrawing.ObjectDrawing;
import com.pao.game.view.RegionPainter;
import com.pao.game.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DrawingBoard {
    final float TIME_FONT_SIZE=ViewConstants.getFloatConstant("DrawingBoard.Time.Font.Size");
    final float PLATES_FONT_SIZE=ViewConstants.getFloatConstant("DrawingBoard.Plates.Font.Size");
    final float BULLETS_FONT_SIZE=ViewConstants.getFloatConstant("DrawingBoard.Bullets.Font.Size");
    final float BACKGROUND_COLOR_R=ViewConstants.getFloatConstant("DrawingBoard.BackGround.R");
    final float BACKGROUND_COLOR_G=ViewConstants.getFloatConstant("DrawingBoard.BackGround.G");
    final float BACKGROUND_COLOR_B=ViewConstants.getFloatConstant("DrawingBoard.BackGround.B");
    final float BACKGROUND_COLOR_A=ViewConstants.getFloatConstant("DrawingBoard.BackGround.A");
    final float TOP_COLOR_R=ViewConstants.getFloatConstant("DrawingBoard.Top.Color.R");
    final float TOP_COLOR_G=ViewConstants.getFloatConstant("DrawingBoard.Top.Color.G");
    final float TOP_COLOR_B=ViewConstants.getFloatConstant("DrawingBoard.Top.Color.B");
    final float TOP_COLOR_A=ViewConstants.getFloatConstant("DrawingBoard.Top.Color.A");
    final float TIME_FONT_COLOR_R=ViewConstants.getFloatConstant("DrawingBoard.Time.Color.R");
    final float TIME_FONT_COLOR_G=ViewConstants.getFloatConstant("DrawingBoard.Time.Color.G");
    final float TIME_FONT_COLOR_B=ViewConstants.getFloatConstant("DrawingBoard.Time.Color.B");
    final float TIME_FONT_COLOR_A=ViewConstants.getFloatConstant("DrawingBoard.Time.Color.A");
    final Drop game;
    final OrthographicCamera camera;
    final ViewModel VM;
    Viewport viewport;
    PrepareDrawingObjects preparator;
    RegionPainter painterGame;
    RegionPainter painterTop;


    public DrawingBoard(final Drop game,final ViewModel VM)
    {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.VM = VM;
        camera.setToOrtho(false, Drop.WIDTH,Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH,Drop.HEIGHT,camera);
        painterGame = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT-100,1920,954,new Color(BACKGROUND_COLOR_R,BACKGROUND_COLOR_G,BACKGROUND_COLOR_B,BACKGROUND_COLOR_A));
        painterTop = new RegionPainter(game.batch,0,Drop.HEIGHT-100,Drop.WIDTH,100,Drop.WIDTH,100,new Color(TOP_COLOR_R,TOP_COLOR_G,TOP_COLOR_B,TOP_COLOR_A));
        preparator = new PrepareDrawingObjects(painterGame,painterTop);
        painterTop.addFont("Time", (int) TIME_FONT_SIZE,new Color(TIME_FONT_COLOR_R,TIME_FONT_COLOR_G,TIME_FONT_COLOR_B,TIME_FONT_COLOR_A));
        painterTop.addFont("Plates", (int) PLATES_FONT_SIZE,Color.BLACK);
        painterTop.addFont("Bullets", (int) BULLETS_FONT_SIZE,Color.BLACK);
    }
    public void draw(){
        ScreenUtils.clear(BACKGROUND_COLOR_R,BACKGROUND_COLOR_G,BACKGROUND_COLOR_B,BACKGROUND_COLOR_A);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painterGame.fillBackground();
        painterTop.fillBackground();
        List<ObjectDrawing> objectsDrowings = new ArrayList<>();
        preparator.prepare(VM.getObjectDescriptionList(),objectsDrowings);
        for(ObjectDrawing objectDrawing : objectsDrowings) {
            objectDrawing.draw();
        }
        painterTop.drowWriting("Time", "Time: " + (int)VM.getRemainingTime(), Drop.WIDTH/2, 50);
        game.batch.end();
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    public void dispose() {
        painterGame.dispose();
    }
}
