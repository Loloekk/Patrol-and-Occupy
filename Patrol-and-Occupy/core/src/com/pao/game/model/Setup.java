package com.pao.game.model;

import com.pao.game.viewmodel.ColoredParams;
import com.pao.game.viewmodel.MyColor;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = 1920;
    static int height = 1080;
    public static List<Setup> setupList = new ArrayList<>();
    static{
        // Setup 0. (Empty)
        {
            List<Obstacle> obstacleList = new ArrayList<>();

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList));
        }
        // Setup 1.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle(400, 700, 50, 2, 6, 30));
            obstacleList.addAll(Obstacle.rectangleObstacle(400, 400, 50, 4, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0));

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList));
        }
        // Setup 2.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle(500, 500, 80, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 80, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 80, 1, 3, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(200, 600, 80, 1, 5, 0));

            final float off = 300;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList));
        }
        // Setup 3.
        {
            List<Obstacle> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle((float) width /2, (float) height /2, 500, 1, 1, 0));

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList));
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
