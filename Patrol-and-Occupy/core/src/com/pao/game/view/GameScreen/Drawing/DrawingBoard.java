package com.pao.game.view.GameScreen.Drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.view.Drop;
import com.pao.game.view.GameScreen.Drawing.ObjectDrawing.ObjectDrawing;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.EditSettings;
import com.pao.game.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DrawingBoard {
    final Drop game;
    final OrthographicCamera camera;
    final ViewModel VM;
    Viewport viewport;
    PrepareDrawingObjects preparator;
    RegionPainter painterGame;
    RegionPainter painterTop;


    public DrawingBoard(final Drop game,final ViewModel VM, int n)
    {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.VM = VM;
        camera.setToOrtho(false, Drop.WIDTH,Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH,Drop.HEIGHT,camera);
        painterGame = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT-100,1920,954,new Color(0.9f,0.9f,0.4f,1));
        painterTop = new RegionPainter(game.batch,0,Drop.HEIGHT-100,Drop.WIDTH,100,Drop.WIDTH,100,new Color(0.274f,0.0f,0.305f,1));
        preparator = new PrepareDrawingObjects(painterGame,painterTop);
        painterTop.addFont("Time",40,new Color(0.9f,0.9f,0.4f,1));
        painterTop.addFont("Plates",40,Color.BLACK);
        painterTop.addFont("Magazines",28,Color.BLACK);
    }
    public void draw(){
        ScreenUtils.clear(0.9f,0.9f,0.4f,1);
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
        painterTop.drowWriting("Time", "Time: " + (int)VM.getRemainingTime(), 960, 50);
        game.batch.end();
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    public void dispose() {
        painterGame.dispose();
    }
}
