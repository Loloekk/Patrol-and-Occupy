package com.pao.game.view.GameScreen.PlayerView;

import com.badlogic.gdx.Gdx;
import com.pao.game.communication.Move;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.ViewSettings;
import com.pao.game.viewmodel.ViewModel;
import java.util.List;

public class PlayerView5Buttons implements PlayerView{
    ModelPlayer color;
    ViewModel VM;
    int up;
    int down;
    int left;
    int right;
    int shoot;
    int placeDynamite;
    boolean lastShoot;
    boolean lastStateUp;
    boolean lastStateDown;
    boolean lastStateLeft;
    boolean lastStateRight;
    boolean lastPlaceDynamite;
    public PlayerView5Buttons(ModelPlayer color, ViewModel VM)
    {
        this.color = color;
        this.VM=VM;
        List<Integer> buttons = ViewSettings.getButtons(color);
        if(buttons == null){
            up = -1;
        }
        else {
            up = buttons.get(0);
            down = buttons.get(1);
            left = buttons.get(2);
            right = buttons.get(3);
            shoot = buttons.get(4);
            placeDynamite = buttons.get(5);
        }
        lastShoot = false;
        lastPlaceDynamite = false;
        lastStateUp = false;
        lastStateDown = false;
        lastStateLeft = false;
        lastStateRight = false;
    }
    public void update()
    {
        if(up == -1) return;
        if(Gdx.input.isKeyPressed(up) && lastStateUp == false){
            lastStateUp = true;
            VM.setMove(color, Move.Forward,true);
        }
        if(Gdx.input.isKeyPressed(down) && lastStateDown == false){
            lastStateDown = true;
            VM.setMove(color, Move.Back,true);
        }
        if(Gdx.input.isKeyPressed(left) && lastStateLeft == false){
            lastStateLeft = true;
            VM.setMove(color, Move.Left,true);
        }
        if(Gdx.input.isKeyPressed(right) && lastStateRight == false){
            lastStateRight = true;
            VM.setMove(color, Move.Right,true);
        }
        if(Gdx.input.isKeyPressed(up) == false && lastStateUp){
            lastStateUp = false;
            VM.setMove(color, Move.Forward,false);
        }
        if(Gdx.input.isKeyPressed(down) == false && lastStateDown ){
            lastStateDown = false;
            VM.setMove(color, Move.Back,false);
        }
        if(Gdx.input.isKeyPressed(left) == false && lastStateLeft){
            lastStateLeft = false;
            VM.setMove(color, Move.Left,false);
        }
        if(Gdx.input.isKeyPressed(right) == false && lastStateRight){
            lastStateRight = false;
            VM.setMove(color, Move.Right,false);
        }
        if(Gdx.input.isKeyPressed(shoot)&& lastShoot==false){
            lastShoot = true;
            VM.setMove(color,Move.Shoot,true);
        }
        if(Gdx.input.isKeyPressed(shoot)==false&& lastShoot){
            lastShoot = false;
            VM.setMove(color,Move.Shoot,false);
        }
        if(Gdx.input.isKeyPressed(placeDynamite)&& lastPlaceDynamite==false){
            lastPlaceDynamite = true;
            VM.setMove(color,Move.Dynamite,true);
        }
        if(Gdx.input.isKeyPressed(placeDynamite)==false&& lastPlaceDynamite){
            lastPlaceDynamite = false;
            VM.setMove(color,Move.Dynamite,false);
        }
    }
}