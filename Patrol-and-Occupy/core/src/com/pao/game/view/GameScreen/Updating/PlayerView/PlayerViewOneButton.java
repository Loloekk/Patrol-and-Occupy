package com.pao.game.view.GameScreen.Updating.PlayerView;

import com.badlogic.gdx.Gdx;
import com.pao.game.communication.Move;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.ViewSettings;
import com.pao.game.viewmodel.ViewModel;

public class PlayerViewOneButton implements PlayerView{
    ModelPlayer color;
    ViewModel VM;
    int up;
    boolean lastStateUp;
    public PlayerViewOneButton(ModelPlayer color, ViewModel VM)
    {
        this.color = color;
        this.VM=VM;
        up = ViewSettings.getButton(color);
        lastStateUp = false;
    }
    public void update()
    {
        if(Gdx.input.isKeyPressed(up) && lastStateUp == false){
            lastStateUp = true;
            VM.setMove(color, Move.Forward,true);
        }
        if(Gdx.input.isKeyPressed(up) == false && lastStateUp){
            lastStateUp = false;
            VM.setMove(color, Move.Forward,false);
        }
    }
}
