package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.model.*;
import com.pao.game.viewmodel.*;
import com.pao.game.viewmodel.MyColor.*;
import com.pao.game.viewmodel.*;
import com.sun.java.accessibility.util.SwingEventMonitor;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    ViewModel VM;
    List<PlayerView> players;
    Textures text;
    Viewport viewport;
    Texture ground;
    BitmapFont font;
    long startTime;
    RegionPainter painterGame;
    RegionPainter painterRight;
    RegionPainter painterLeft;
    RegionPainter painterTop;

    public GameScreen(final Drop game,int n,ViewModel VM){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH,Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH,Drop.HEIGHT,camera);
        this.VM=VM;
        players = new ArrayList<>();
        List<MyColor> colors = MyColor.getColorList(n);
        if(n>=1)players.add(new PlayerView(colors.get(0), Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.CONTROL_RIGHT));
        if(n>=2)players.add(new PlayerView(colors.get(1), Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.SPACE));
        text = new Textures(n);

        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = com.badlogic.gdx.graphics.Color.RED;
        font = generator.generateFont(parameter);
        startTime = TimeUtils.nanoTime();
        painterGame = new RegionPainter(game.batch,100,0,Drop.WIDTH-200,Drop.HEIGHT-100,1920,1080,new Color(0.9f,0.9f,0.4f,1));
        painterLeft = new RegionPainter(game.batch,0,0,100,Drop.HEIGHT-100,100,Drop.HEIGHT-100,new Color(0f,1f,0f,1));
        painterRight = new RegionPainter(game.batch,Drop.WIDTH-100,0,100,Drop.HEIGHT-100,100,Drop.HEIGHT-100,new Color(0f,1f,0f,1));
        painterTop = new RegionPainter(game.batch,0,Drop.HEIGHT-100,Drop.WIDTH,100,Drop.WIDTH,100,new Color(0f,0f,1f,1));
    }
    public void update() {
        for(PlayerView player : players)
        {
            if(Gdx.input.isKeyPressed(player.getUp()) && player.getLastStateUp() == false){
                player.setLastStateUp(true);
                VM.setMove(player.getColor(), Move.F,true);
            }
            if(Gdx.input.isKeyPressed(player.getDown()) && player.getLastStateDown() == false){
                player.setLastStateDown(true);
                VM.setMove(player.getColor(), Move.B,true);
            }
            if(Gdx.input.isKeyPressed(player.getLeft()) && player.getLetLastStateLeft() == false){
                player.setLastStateLeft(true);
                VM.setMove(player.getColor(), Move.L,true);
            }
            if(Gdx.input.isKeyPressed(player.getRight()) && player.getLastStateRight() == false){
                player.setLastStateRight(true);
                VM.setMove(player.getColor(), Move.R,true);
            }
            if(Gdx.input.isKeyPressed(player.getUp()) == false && player.getLastStateUp()){
                player.setLastStateUp(false);
                VM.setMove(player.getColor(), Move.F,false);
            }
            if(Gdx.input.isKeyPressed(player.getDown()) == false && player.getLastStateDown() ){
                player.setLastStateDown(false);
                VM.setMove(player.getColor(), Move.B,false);
            }
            if(Gdx.input.isKeyPressed(player.getLeft()) == false && player.getLetLastStateLeft()){
                player.setLastStateLeft(false);
                VM.setMove(player.getColor(), Move.L,false);
            }
            if(Gdx.input.isKeyPressed(player.getRight()) == false && player.getLastStateRight()){
                player.setLastStateRight(false);
                VM.setMove(player.getColor(), Move.R,false);
            }
            if(Gdx.input.isKeyPressed(player.getShoot())&& player.getLastShoot()==false){
                player.setLastShoot(true);
                VM.setMove(player.getColor(),Move.S,true);
            }
            if(Gdx.input.isKeyPressed(player.getShoot())==false&& player.getLastShoot()){
                player.setLastShoot(false);
                VM.setMove(player.getColor(),Move.S,false);
            }
        }
    }
    public void draw() {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painterLeft.fillBackground();
        painterRight.fillBackground();
        painterTop.fillBackground();
        painterGame.fillBackground();
        //game.batch.draw(ground,0,0,width,height);
        for(Params obstacle : VM.getObstacles()) {
            painterGame.draw(new TextureRegion(text.getObstacleTexture()),obstacle,1);
            //game.batch.draw(textureRegion,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,obstacle.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,obstacle.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(ColoredParams bullet : VM.getBullets()) {
            painterGame.draw(new TextureRegion(text.getBulletTexture()),bullet);
            //game.batch.draw(textureRegion,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,bullet.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,bullet.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(ColoredParams tank : VM.getTanks()) {
            painterGame.draw(new TextureRegion(text.getTankTexture(tank.getColor())),tank);
            //game.batch.draw(region, X-H/2,Y-W/2,H/2,W/2,H+5,W,1,1,tank.getRotation());
            //game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H+5,W,1,1,tank.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        int elapsedSeconds = (int) VM.getRemainingTime();
        font.draw(game.batch, "Czas: " + elapsedSeconds, 900, 1000);
        game.batch.end();
    }
    @Override
    public void render(float delta){
        update();
        draw();
    }
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        text.dispose();
        ground.dispose();
        font.dispose();
        painterGame.dispose();
    }


}