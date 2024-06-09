package com.pao.game.view.GameScreen.Updating;

import com.pao.game.model.ModelPlayer;
import com.pao.game.view.GameScreen.Updating.PlayerView.PlayerView;
import com.pao.game.view.GameScreen.Updating.PlayerView.PlayerView5Buttons;
import com.pao.game.view.GameScreen.Updating.PlayerView.PlayerViewOneButton;
import com.pao.game.viewmodel.EditSettings;
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
        for(int i = 0 ;i < n ;i ++){
            if(!EditSettings.getControl()){
                players.add((PlayerView) new PlayerView5Buttons(colors.get(i),VM));
            }
            else {
                players.add((PlayerView) new PlayerViewOneButton(colors.get(i),VM));
            }
        }
    }
    public void update(float time)
    {
        VM.update(time);
        for(PlayerView player : players){
            player.update();
        }
    }
}
