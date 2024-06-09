package com.pao.game.viewmodel;

import com.pao.game.model.ModelPlayer;
import com.pao.game.model.GameObject.Others.Tank.Tank;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<ModelPlayer> getWinners() {
        List<ModelPlayer> players = getPlayers();
        players.sort(Comparator.comparing(this::getNumberOfPlates).thenComparing(this::getKillNumber).thenComparing(Comparator.comparing(this::getDeadNumber).reversed()));
        ModelPlayer first = players.get(players.size()-1);
        return players.stream().filter(p -> getNumberOfPlates(p) == getNumberOfPlates(first)
                                        &&  getKillNumber(p) == getKillNumber(first)
                                        &&  getDeadNumber(p) == getDeadNumber(first))
                               .collect(Collectors.toList());
    }
}
