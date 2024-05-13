package com.pao.game.model;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    List<Obstacle> obstacleList;
    List<Tank> tankList;

    public Setup(List<Tank> tankList, List<Obstacle> obstacleList){
        this.tankList = tankList;
        this.obstacleList = obstacleList;
        if(tankList==null) this.tankList = new ArrayList<>();
        if(obstacleList==null) this.obstacleList = new ArrayList<>();
    }

    public List<Obstacle> getObstacleList(){
        return obstacleList;
    }
    public List<Tank> getTankList(){
        return tankList;
    }
}
