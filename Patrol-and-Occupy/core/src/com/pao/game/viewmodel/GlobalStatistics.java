package com.pao.game.viewmodel;

import com.pao.game.model.ModelPlayer;
import com.pao.game.model.PlayerStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalStatistics {
    static Map<ModelPlayer, PlayerStatistics> colorMap;
    static {
        colorMap = new HashMap<>();
    }
    public static void addPlayers(List<ModelPlayer> colorList) {
        for(ModelPlayer color : colorList) {
            colorMap.put(color, new PlayerStatistics(color));
        }
    }
    public static List<ModelPlayer> getPlayers() { return new ArrayList<>(colorMap.keySet()); }
    public static void incrementNumberOfPlates(ModelPlayer color) { colorMap.get(color).incrementNumberOfPlates(); }
    public static void decrementNumberOfPlates(ModelPlayer color) { colorMap.get(color).decrementNumberOfPlates(); }
    public static void incrementKillNumber(ModelPlayer color) { colorMap.get(color).incrementKillNumber(); }
    public static void incrementDeadNumber(ModelPlayer color) { colorMap.get(color).incrementDeadNumber(); }
    public static int getNumberOfPlates(ModelPlayer color) { return colorMap.get(color).getNumberOfPlates(); }
    public static int getKillNumber(ModelPlayer color) { return colorMap.get(color).getKillNumber(); }
    public static int getDeadNumber(ModelPlayer color) { return colorMap.get(color).getDeadNumber(); }
}
