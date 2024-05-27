package com.pao.game.viewmodel;

import com.pao.game.model.Board;
import com.pao.game.model.ModelPlayer;
import com.pao.game.model.PlayerStatistics;
import com.pao.game.model.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        players.sort((p1, p2) -> Integer.compare(getNumberOfPlates(p1), getNumberOfPlates(p2)));
        players.sort((p1, p2) -> Integer.compare(getKillNumber(p1), getKillNumber(p2)));
        players.sort((p1, p2) -> Integer.compare(getDeadNumber(p2), getDeadNumber(p1)));
        return players.get(players.size()-1);
    }
}
