package com.pao.game.view.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.view.Animations;
import com.pao.game.view.Drop;
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
        preparator = new PrepareDrawingObjects();
        painterGame = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT-100,1920,954,new Color(0.9f,0.9f,0.4f,1));
        painterTop = new RegionPainter(game.batch,0,Drop.HEIGHT-100,Drop.WIDTH,100,Drop.WIDTH,100,new Color(0.274f,0.0f,0.305f,1));
        painterTop.addFont("Time",40,new Color(0.9f,0.9f,0.4f,1));
        painterTop.addFont("Plates",40,Color.BLACK);
        painterTop.addFont("Magazines",28,Color.BLACK);
    }
    public void draw(){
        ScreenUtils.clear(0f, 0f, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painterTop.fillBackground();
        painterGame.fillBackground();
        List<MagazineView> magazines = new ArrayList<>();
        List<DrawingObject> drawingObjects = new ArrayList<>();
        preparator.prepare(VM.getObjectDescriptionList(),magazines,drawingObjects);
        for(DrawingObject drawingObject : drawingObjects) {
            painterGame.drawTexture(drawingObject.getTexture(),drawingObject.getDescription());
        }
        for(MagazineView magazine : magazines){
            drowMagazine(magazine);
        }

        painterTop.drowWriting("Time", "Time: " + (int)VM.getRemainingTime(), 960, 50);
        game.batch.end();
    }
    private void drowMagazine(MagazineView magazine)
    {
        painterTop.drawTexture(new TextureRegion(Textures.getMagazineTexture(magazine.getColor())), magazine.getX(), magazine.getY(), 300,95,0);
        painterTop.drawTexture(new TextureRegion(Textures.getPlateTexture(null)),magazine.getX()-80,magazine.getY(),80,80,0);
        painterTop.drawTexture(new TextureRegion(Textures.getBulletTexture()),magazine.getX()+60,magazine.getY()-10,150,50,0);
        if(magazine.getDynamite()){
            painterTop.drawTexture(new TextureRegion(Textures.getDynamiteTexture()),magazine.getX()+120,magazine.getY()+25,30,30,0);
        }
        painterTop.drowWriting("Plates",((Integer)magazine.getPlates()).toString(),magazine.getX()-80,magazine.getY());
        painterTop.drowWriting("Magazines",(Integer)magazine.getBullets()+"/"+ EditSettings.getMagazineCapacity(),magazine.getX()+35,magazine.getY()-10);
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    public void dispose() {
        painterGame.dispose();
    }
}
