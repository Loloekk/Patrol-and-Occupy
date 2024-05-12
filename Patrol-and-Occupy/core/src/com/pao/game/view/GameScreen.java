package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.pao.game.model.*;
import com.pao.game.viewmodel.*;
import com.pao.game.viewmodel.Color.*;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    ViewModel VM;
    List<PlayerView> players;
    Textures text;
    BitmapFont font;
    long startTime;

    public GameScreen(final Drop game,int n){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000,800);
        //camera.position.set(350,223,0);
        VM=new ViewModel(1000,800,n);
        players = new ArrayList<>();
        if(n>=1)players.add(new PlayerView(Color.R, Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.SPACE));
        if(n>=2)players.add(new PlayerView(Color.B, Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.CONTROL_RIGHT));
        text = new Textures(n);

        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.color = com.badlogic.gdx.graphics.Color.RED;
        font = generator.generateFont(parameter);
        startTime = TimeUtils.nanoTime();
    }
    @Override
    public void render(float time){
        ScreenUtils.clear(0.9f, 0.9f, 0.4f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

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
            game.batch.draw(texture,X-H/2,Y-W/2,H/2,W/2,H+10,W,1,1,tank.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
//            System.out.println(X);
//            System.out.println(Y);
//            System.out.println("");
            //spriteBatch.draw(texture, x, y, originX, originY, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
        long elapsedTime = TimeUtils.timeSinceNanos(startTime);
        long elapsedSeconds = elapsedTime / 1_000_000_000;
        font.draw(game.batch, "Czas: " + elapsedSeconds, 400, 800);
        game.batch.end();
        for(PlayerView player : players)
        {
            if(Gdx.input.isKeyPressed(player.up) && player.lastStateUp == false){
                player.lastStateUp = true;
                VM.setMove(player.color, Move.F,true);
            }
            if(Gdx.input.isKeyPressed(player.down) && player.lastStateDown == false){
                player.lastStateDown = true;
                VM.setMove(player.color, Move.B,true);
            }
            if(Gdx.input.isKeyPressed(player.left) && player.lastStateLeft == false){
                player.lastStateLeft = true;
                VM.setMove(player.color, Move.L,true);
            }
            if(Gdx.input.isKeyPressed(player.right) && player.lastStateRight == false){
                player.lastStateRight = true;
                VM.setMove(player.color, Move.R,true);
            }
            if(Gdx.input.isKeyPressed(player.up) == false && player.lastStateUp){
                player.lastStateUp = false;
                VM.setMove(player.color, Move.F,false);
            }
            if(Gdx.input.isKeyPressed(player.down) == false && player.lastStateDown ){
                player.lastStateDown = false;
                VM.setMove(player.color, Move.B,false);
            }
            if(Gdx.input.isKeyPressed(player.left) == false && player.lastStateLeft){
                player.lastStateLeft = false;
                VM.setMove(player.color, Move.L,false);
            }
            if(Gdx.input.isKeyPressed(player.right) == false && player.lastStateRight){
                player.lastStateRight = false;
                VM.setMove(player.color, Move.R,false);
            }
            if(Gdx.input.isKeyPressed(player.shot)&& player.lastshoot==false){
                player.lastshoot=true;
                VM.shoot(player.color);
            }
            if(Gdx.input.isKeyPressed(player.shot)==false&& player.lastshoot){
                player.lastshoot=false;
            }
        }
        VM.update(time);
    }
    public void resize(int width, int height) {
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
    }


}