package com.pao.game.model;

import java.util.List;
import java.util.Map;

public class GlobalStatistics {
    Map<ModelPlayer, PlayerStatistics> colorMap;
    public GlobalStatistics(List<ModelPlayer> colorList) {
        for(ModelPlayer color : colorList) {
            colorMap.put(color, new PlayerStatistics(color));
        }
    }
    public void incrementNumberOfPlates(ModelPlayer color) { colorMap.get(color).incrementNumberOfPlates(); }
    public void decrementNumberOfPlates(ModelPlayer color) { colorMap.get(color).decrementNumberOfPlates(); }
    public void incrementKillNumber(ModelPlayer color) { colorMap.get(color).incrementKillNumber(); }
    public void incrementDeadNumber(ModelPlayer color) { colorMap.get(color).incrementDeadNumber(); }
    public int getNumberOfPlates(ModelPlayer color) { return colorMap.get(color).getNumberOfPlates(); }
    public int getKillNumber(ModelPlayer color) { return colorMap.get(color).getKillNumber(); }
    public int getDeadNumber(ModelPlayer color) { return colorMap.get(color).getDeadNumber(); }
}
