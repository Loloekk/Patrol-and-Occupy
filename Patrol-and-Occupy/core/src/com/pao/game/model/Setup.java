package com.pao.game.model;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = 1080;
    static int height = 1920;
    static List<Setup> setupList;
    static{
        // Setup 1.
        {
            List<Tank> tankList = new ArrayList<>();
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.horizontalObstacle(400, 700, 50, 6));
            obstacleList.addAll(Obstacle.verticalObstacle(400, 400, 50, 4));
            obstacleList.addAll(Obstacle.horizontalObstacle(1000, 200, 50, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(1300, 800, 50, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(900, 600, 50, 2));
            Setup setup = new Setup(tankList, obstacleList);
            setupList.add(setup);
        }
        // Setup 2.
        {
            List<Tank> tankList = new ArrayList<>();
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.verticalObstacle(400, 600, 30, 2));
            obstacleList.addAll(Obstacle.horizontalObstacle(500, 500, 30, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(1000, 200, 30, 5));
            obstacleList.addAll(Obstacle.horizontalObstacle(1300, 800, 30, 3));
            obstacleList.addAll(Obstacle.horizontalObstacle(200, 600, 30, 5));
            Setup setup = new Setup(tankList, obstacleList);
            setupList.add(setup);
        }
        // Setup 3.
        {
            List<Tank> tankList = new ArrayList<>();
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.verticalObstacle(400, 600, 200, 1));
            Setup setup = new Setup(tankList, obstacleList);
            setupList.add(setup);
        }
    }
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
