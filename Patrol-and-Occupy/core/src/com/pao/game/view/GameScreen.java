package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
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
    int width;
    int height;

    public GameScreen(final Drop game,int n){
        this.game=game;
        camera = new OrthographicCamera();
        width = 1000;
        height = 800;
        camera.setToOrtho(false, width,height);
        viewport = new ExtendViewport(width,height,camera);
        VM=new ViewModel(width,height,n);
        players = new ArrayList<>();
        if(n>=1)players.add(new PlayerView(MyColor.R, Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.CONTROL_RIGHT));
        if(n>=2)players.add(new PlayerView(MyColor.B, Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.SPACE));
        text = new Textures(n);

        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = com.badlogic.gdx.graphics.Color.RED;
        font = generator.generateFont(parameter);
        startTime = TimeUtils.nanoTime();
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.9f,0.9f,0.4f,1);
        pixmap.fillRectangle(0, 0, width, height);
        ground = new Texture(pixmap);
        pixmap.dispose(); // Wymagane aby zwolnić zasoby Pixmap
    }
    @Override
    public void render(float time){
        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(ground,0,0,width,height);
        for(Params obstacle : VM.getObstacles()) {
            Texture texture = text.getObstacleTexture();
            float X = obstacle.getX();
            float Y = obstacle.getY();
            float W = obstacle.getWidht();
            float H = obstacle.getHeight();
            game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,obstacle.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
        }
        for(ColoredParams bullet : VM.getBullets()) {
            Texture texture =text.getBulletTexture();
            float X=bullet.getX();
            float Y=bullet.getY();
            float W=bullet.getWidht();
            float H=bullet.getHeight();
            game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H,W,1,1,bullet.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);

        }
        for(ColoredParams tank : VM.getTanks()) {
            Texture texture =text.getTankTexture(tank.getColor());
            float X=tank.getX();
            float Y=tank.getY();
            float W=tank.getWidht();
            float H=tank.getHeight();
            game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H+5,W,1,1,tank.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
//            System.out.println(X);
//            System.out.println(Y);
//            System.out.println("");
            //spriteBatch.draw(texture, x, y, originX, originY, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
        long elapsedTime = TimeUtils.timeSinceNanos(startTime);
        long elapsedSeconds = elapsedTime / 1_000_000_000;
        font.draw(game.batch, "Czas: " + elapsedSeconds, 400, 800);
        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
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
            }
        }
        VM.update(time);
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
    }


}