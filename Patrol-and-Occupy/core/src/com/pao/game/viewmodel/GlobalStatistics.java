package com.pao.game.viewmodel;

import com.pao.game.model.ModelPlayer;
import com.pao.game.model.GameObject.Tank;

import java.util.*;

public class GlobalStatistics {
    ViewModel VM;
    public GlobalStatistics(ViewModel VM)
    {
        this.VM = VM;
    }
    public static List<ModelPlayer> getPlayers() { return ModelPlayer.getColorList(EditSettings.getNumberOfPlayers()); }
    public int getNumberOfPlates(ModelPlayer color) {
        Tank tank = VM.getTank(color);
        if(tank == null){
            return 0;
        }
        return tank.getStatistics().getNumberOfPlates();
    }
    public int getKillNumber(ModelPlayer color) {
        Tank tank = VM.getTank(color);
        if(tank == null){
            return 0;
        }
        return tank.getStatistics().getKillNumber();
    }
    public int getDeadNumber(ModelPlayer color) {
        Tank tank = VM.getTank(color);
        if(tank == null){
            return 0;
        }
        return tank.getStatistics().getDeadNumber();
    }
    public ModelPlayer getWinner() {
        List<ModelPlayer> players = getPlayers();
        players.sort(Comparator.comparing(this::getNumberOfPlates).thenComparing(this::getKillNumber).thenComparing(Comparator.comparing(this::getDeadNumber).reversed()));
        return players.get(players.size()-1);
    }
}
