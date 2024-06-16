package com.pao.game.model.GameObject.Others.Tank;

public class PlayerStatistics {
    int numberOfPlates;
    int killNumber;
    int deadNumber;
    public PlayerStatistics() {
        numberOfPlates = 0;
        killNumber = 0;
        deadNumber = 0;
    }
    public void incrementNumberOfPlates() { numberOfPlates++; }
    public void decrementNumberOfPlates() { numberOfPlates--; }
    public void incrementKillNumber() { killNumber++; }
    public void incrementDeadNumber() { deadNumber++; }
    public int getNumberOfPlates() { return numberOfPlates; }
    public int getKillNumber() { return killNumber; }
    public int getDeadNumber() { return deadNumber; }
}
