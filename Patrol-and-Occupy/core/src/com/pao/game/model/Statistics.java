package com.pao.game.model;

public class Statistics {
    int numberOfPlates;
    int killNumber;
    int deadNumber;
    public Statistics() {

    }
    public void incrementNumberOfPlates() { numberOfPlates++; }
    public void incrementKillNumber() { killNumber++; }
    public void incrementDeadNumber() { deadNumber++; }
    public int getNumberOfPlates() { return numberOfPlates; }
    public int getKillNumber() { return killNumber; }
    public int getDeadNumber() { return deadNumber; }

}
