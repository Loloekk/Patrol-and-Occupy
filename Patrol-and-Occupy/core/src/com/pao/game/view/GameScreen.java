package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pao.game.model.*;
import com.pao.game.viewmodel.Color;
import com.pao.game.viewmodel.Color.*;
import com.pao.game.viewmodel.ColoredParams;
import com.pao.game.viewmodel.Move;
import com.pao.game.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    ViewModel VM;
    List<PlayerView> players;
    Textures text;

    public GameScreen(final Drop game,int n){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000,800);
        VM=new ViewModel(1000,800,n);
        players = new ArrayList<>();
        if(n>=1)players.add(new PlayerView(Color.R, Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT));
        if(n>=2)players.add(new PlayerView(Color.B, Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D));
        text = new Textures(n);
    }
    @Override
    public void render(float time){
        ScreenUtils.clear(0.9f, 0.9f, 0.4f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for(ColoredParams bullet : VM.getBullets()) {
            game.batch.draw(text.getBulletTexture(),bullet.getX(),bullet.getY());
        }
        for(ColoredParams tank : VM.getTanks()) {
            Texture texture =text.getTankTexture(tank.getColor());
            float X=tank.getX();
            float Y=tank.getY();
            float W=tank.getWidht();
            float H=tank.getHeight();
            game.batch.draw(texture,X-H/2,Y-W/2,0,0,H,W,1,1,tank.getRotation(),0,0,texture.getWidth(), texture.getHeight(), false, false);
//            System.out.println(X);
//            System.out.println(Y);
//            System.out.println("");
            //spriteBatch.draw(texture, x, y, originX, originY, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
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