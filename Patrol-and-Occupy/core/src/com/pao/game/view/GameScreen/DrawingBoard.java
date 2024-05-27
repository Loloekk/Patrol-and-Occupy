package com.pao.game.view.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.Communication.ColoredParams;
import com.pao.game.Communication.Params;
import com.pao.game.view.Drop;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.ViewModel;

import java.util.List;

public class DrawingBoard {
    final Drop game;
    final OrthographicCamera camera;
    Viewport viewport;
    final ViewModel VM;
    Textures text;
    RegionPainter painterGame;
    RegionPainter painterTop;

    public DrawingBoard(final Drop game,final ViewModel VM, int n)
    {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.VM = VM;
        camera.setToOrtho(false, Drop.WIDTH,Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH,Drop.HEIGHT,camera);
        text = new Textures(n);
        painterGame = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT-100,1920,954,new Color(0.9f,0.9f,0.4f,1));
        painterTop = new RegionPainter(game.batch,0,Drop.HEIGHT-100,Drop.WIDTH,100,Drop.WIDTH,100,new Color(0f,0f,1f,1));
        painterTop.addFont("Czas",40,Color.RED);
    }
    public void draw(){
        ScreenUtils.clear(0f, 0f, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        painterTop.fillBackground(1f);
        painterGame.fillBackground(1f);
        //game.batch.draw(ground,0,0,width,height);
        for(ColoredParams spawn : VM.getSpawns()) {
            painterGame.drawTexture(new TextureRegion(text.getSpawnTexture(spawn.getColor())), spawn);
        }
        for(ColoredParams plate: VM.getPlates()) {
            painterGame.drawTexture(new TextureRegion(text.getPlateTexture(plate.getColor())), plate);
        }
        for(Params obstacle : VM.getObstacles()) {
            painterGame.drawTexture(new TextureRegion(text.getObstacleTexture()),obstacle,1.03f);
            //game.batch.draw(textureRegion,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,obstacle.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,obstacle.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(Params breakableObstacle : VM.getBreakableObstacles()) {
            painterGame.drawTexture(new TextureRegion(text.getObstacleTexture()),breakableObstacle,1.03f);
        }

        for(ColoredParams bullet : VM.getBullets()) {
            painterGame.drawTexture(new TextureRegion(text.getBulletTexture()),bullet);
            //game.batch.draw(textureRegion,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,bullet.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,bullet.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(ColoredParams tank : VM.getTanks()) {
            painterGame.drawTexture(new TextureRegion(text.getTankTexture(tank.getColor())),tank,1.03f);
            //game.batch.draw(region, X-H/2,Y-W/2,H/2,W/2,H+5,W,1,1,tank.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H+5,W,1,1,tank.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(Params dynamite : VM.getDynamites()) {
            painterGame.drawTexture(new TextureRegion(text.getDynamiteTexture()),dynamite);
        }
        int elapsedSeconds = (int) VM.getRemainingTime();
        painterTop.drowWriting("Czas", "Czas: " + elapsedSeconds, 960, 50);
        game.batch.end();
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    public void dispose() {
        text.dispose();
        painterGame.dispose();
    }
}
