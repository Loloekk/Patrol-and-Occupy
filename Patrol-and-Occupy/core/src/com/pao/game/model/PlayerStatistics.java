package com.pao.game.model;

public class PlayerStatistics {
    ModelPlayer color;
    int numberOfPlates;
    int killNumber;
    int deadNumber;
    public PlayerStatistics(ModelPlayer color) {
        this.color = color;
    }
    public void incrementNumberOfPlates() { numberOfPlates++; }
    public void decrementNumberOfPlates() { numberOfPlates--; }
    public void incrementKillNumber() { killNumber++; }
    public void incrementDeadNumber() { deadNumber++; }
    public int getNumberOfPlates() { return numberOfPlates; }
    public int getKillNumber() { return killNumber; }
    public int getDeadNumber() { return deadNumber; }

}
