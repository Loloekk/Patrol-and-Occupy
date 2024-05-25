package com.pao.game.model;

import com.pao.game.viewmodel.ColoredParams;
import com.pao.game.viewmodel.Params;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = 1720;
    static int height = 954;
    private static List<Setup> setupList = new ArrayList<>();
    static{
        // Setup 0. (Empty)
        {
            List<Plate> plateList = new ArrayList<>();

            List<Params> obstacleList = new ArrayList<>();

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width-off, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList, plateList));
        }
        // Setup 1.
        {
            List<Plate> plateList = new ArrayList<>();
            plateList.add(new Plate(300, 200));
            plateList.add(new Plate(1000, 500));
            plateList.add(new Plate(1500, 300));
            plateList.add(new Plate(450, 900));

            List<Params> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle(400, 700, 50, 2, 6, 30));
            obstacleList.addAll(Obstacle.rectangleObstacle(400, 400, 50, 4, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0));

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width-off, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList, plateList));
        }
        // Setup 2.
        {
            List<Plate> plateList = new ArrayList<>();
            plateList.add(new Plate(300, 200));

            List<Params> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle(500, 500, 80, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 80, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 80, 1, 3, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(200, 600, 80, 1, 5, 0));

            final float off = 300;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width-off, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList, plateList));
        }
        // Setup 3.
        {
            List<Plate> plateList = new ArrayList<>();
            plateList.add(new Plate(300, 200));

            List<Params> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle((float) width /2, (float) height /2, 500, 1, 1, 0));

            final float off = 200;
            List<ColoredParams> tankParamsList = new ArrayList<>();
            tankParamsList.add(new ColoredParams(MyColor.R, 70, 60, width-off, off,0));
            tankParamsList.add(new ColoredParams(MyColor.G,70, 60, width-off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.B,70, 60, off, height-off,0));
            tankParamsList.add(new ColoredParams(MyColor.Y,70, 60, off, off,0));

            setupList.add(new Setup(tankParamsList, obstacleList, plateList));
        }
    }
    List<Params> obstacleList;
    List<Plate> plateList;
    List<ColoredParams> tankParamsList;

    public Setup(List<ColoredParams> tankParamsList, List<Params> obstacleList, List<Plate> plateList){
        this.tankParamsList = tankParamsList;
        this.obstacleList = obstacleList;
        this.plateList = plateList;
        if(tankParamsList==null) this.tankParamsList = new ArrayList<>();
        if(obstacleList==null) this.obstacleList = new ArrayList<>();
        if(plateList==null) this.plateList = new ArrayList<>();
    }

    public List<Params> getObstacleList(){
        return obstacleList;
    }
    public List<Plate> getPlateList() { return plateList; }
    public List<ColoredParams> getTankParamsList(){
        return tankParamsList;
    }
    public static List<Setup> getSetupList()
    {
        return setupList;
    }
}
