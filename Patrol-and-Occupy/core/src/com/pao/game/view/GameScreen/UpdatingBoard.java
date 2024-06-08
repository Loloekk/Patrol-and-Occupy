package com.pao.game.view.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pao.game.communication.Move;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.PlayerView;
import com.pao.game.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpdatingBoard {
    ViewModel VM;
    List<PlayerView> players;

    public UpdatingBoard(ViewModel VM, int n)
    {
        this.VM = VM;
        players = new ArrayList<>();
        List<ModelPlayer> colors = ModelPlayer.getColorList(n);
        if(n>=1)players.add(new PlayerView(colors.get(0), Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.CONTROL_RIGHT,Input.Keys.SLASH));
        if(n>=2)players.add(new PlayerView(colors.get(1), Input.Keys.W,Input.Keys.S,Input.Keys.A,Input.Keys.D,Input.Keys.SPACE,Input.Keys.E));
    }
    public void update(float time)
    {
        VM.update(time);
        for(PlayerView player : players)
        {
            if(Gdx.input.isKeyPressed(player.getUp()) && player.getLastStateUp() == false){
                player.setLastStateUp(true);
                VM.setMove(player.getColor(), Move.Forward,true);
            }
            if(Gdx.input.isKeyPressed(player.getDown()) && player.getLastStateDown() == false){
                player.setLastStateDown(true);
                VM.setMove(player.getColor(), Move.Back,true);
            }
            if(Gdx.input.isKeyPressed(player.getLeft()) && player.getLetLastStateLeft() == false){
                player.setLastStateLeft(true);
                VM.setMove(player.getColor(), Move.Left,true);
            }
            if(Gdx.input.isKeyPressed(player.getRight()) && player.getLastStateRight() == false){
                player.setLastStateRight(true);
                VM.setMove(player.getColor(), Move.Right,true);
            }
            if(Gdx.input.isKeyPressed(player.getUp()) == false && player.getLastStateUp()){
                player.setLastStateUp(false);
                VM.setMove(player.getColor(), Move.Forward,false);
            }
            if(Gdx.input.isKeyPressed(player.getDown()) == false && player.getLastStateDown() ){
                player.setLastStateDown(false);
                VM.setMove(player.getColor(), Move.Back,false);
            }
            if(Gdx.input.isKeyPressed(player.getLeft()) == false && player.getLetLastStateLeft()){
                player.setLastStateLeft(false);
                VM.setMove(player.getColor(), Move.Left,false);
            }
            if(Gdx.input.isKeyPressed(player.getRight()) == false && player.getLastStateRight()){
                player.setLastStateRight(false);
                VM.setMove(player.getColor(), Move.Right,false);
            }
            if(Gdx.input.isKeyPressed(player.getShoot())&& player.getLastShoot()==false){
                player.setLastShoot(true);
                VM.setMove(player.getColor(),Move.Shoot,true);
            }
            if(Gdx.input.isKeyPressed(player.getShoot())==false&& player.getLastShoot()){
                player.setLastShoot(false);
                VM.setMove(player.getColor(),Move.Shoot,false);
            }
            if(Gdx.input.isKeyPressed(player.getPlaceDynamite())&& player.getLastPlaceDynamite()==false){
                player.setLastPlaceDynamite(true);
                VM.setMove(player.getColor(),Move.Dynamite,true);
            }
            if(Gdx.input.isKeyPressed(player.getPlaceDynamite())==false&& player.getLastPlaceDynamite()){
                player.setLastPlaceDynamite(false);
                VM.setMove(player.getColor(),Move.Dynamite,false);
            }

        }
    }
}
