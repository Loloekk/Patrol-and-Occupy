package com.pao.game.model;

import com.pao.game.viewmodel.ColoredParams;
import com.pao.game.viewmodel.MyColor;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = 1080;
    static int height = 1920;
    static List<Setup> setupList;
    static{
        // Setup 1.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.horizontalObstacle(400, 700, 50, 6));
            obstacleList.addAll(Obstacle.verticalObstacle(400, 400, 50, 4));
            obstacleList.addAll(Obstacle.horizontalObstacle(1000, 200, 50, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(1300, 800, 50, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(900, 600, 50, 2));

            final int off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            Setup setup = new Setup(tankParamsList, obstacleList);
            setupList.add(setup);

        }
        // Setup 2.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.verticalObstacle(400, 600, 30, 2));
            obstacleList.addAll(Obstacle.horizontalObstacle(500, 500, 30, 5));
            obstacleList.addAll(Obstacle.verticalObstacle(1000, 200, 30, 5));
            obstacleList.addAll(Obstacle.horizontalObstacle(1300, 800, 30, 3));
            obstacleList.addAll(Obstacle.horizontalObstacle(200, 600, 30, 5));

            final int off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            Setup setup = new Setup(tankParamsList, obstacleList);
            setupList.add(setup);
        }
        // Setup 3.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.verticalObstacle(400, 600, 200, 1));

            final int off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            Setup setup = new Setup(tankParamsList, obstacleList);
            setupList.add(setup);
        }
    }
    List<Obstacle> obstacleList;
    List<ColoredParams> tankParamsList;

    public Setup(List<ColoredParams> tankParamsList, List<Obstacle> obstacleList){
        this.tankParamsList = tankParamsList;
        this.obstacleList = obstacleList;
        if(tankParamsList==null) this.tankParamsList = new ArrayList<>();
        if(obstacleList==null) this.obstacleList = new ArrayList<>();
    }

    public List<Obstacle> getObstacleList(){
        return obstacleList;
    }
    public List<ColoredParams> getTankParamsList(){
        return tankParamsList;
    }
}
