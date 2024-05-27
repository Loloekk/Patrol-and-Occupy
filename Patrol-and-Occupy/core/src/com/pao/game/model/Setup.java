package com.pao.game.model;

import com.pao.game.communication.ColoredParams;
import com.pao.game.communication.Params;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = ModelSettings.getWidth();
    static int height = ModelSettings.getHeight();
    private static final List<Setup> setupList = new ArrayList<>();
    static{
        // Setup 0. (Empty)
        {
            List<Params> plateList = new ArrayList<>();

            List<Params> obstacleList = new ArrayList<>();

            List<Params> breakableObstacleList = new ArrayList<>();

            final float off = 200;
            List<ColoredParams> spawnParamsList = new ArrayList<>();
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player4, 0, 0, width-off, off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player2, 0, 0, width-off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player3, 0, 0, off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player1, 0, 0, off, off,0));

            setupList.add(new Setup(spawnParamsList, obstacleList, breakableObstacleList, plateList));
        }
        // Setup 1.
        {
            List<Params> plateList = new ArrayList<>();
            plateList.add(new Params(300, 200));
            plateList.add(new Params(1000, 500));
            plateList.add(new Params(1500, 300));
            plateList.add(new Params(450, 900));

            List<Params> obstacleList = new ArrayList<>();
//            obstacleList.addAll(Obstacle.rectangleObstacle(400, 700, 50, 2, 6, 30));
//            obstacleList.addAll(Obstacle.rectangleObstacle(400, 400, 50, 4, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0));

            List<Params> breakableObstacleList = new ArrayList<>();
            breakableObstacleList.addAll(BreakableObstacle.rectangleObstacle(400, 700, 50, 2, 6, 30));
            breakableObstacleList.addAll(BreakableObstacle.rectangleObstacle(400, 400, 50, 4, 1, 0));
//            breakableObstacleList.addAll(BreakableObstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0));
//            breakableObstacleList.addAll(BreakableObstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0));
//            breakableObstacleList.addAll(BreakableObstacle.rectangleObstacle(900, 600, 50, 2, 1, 0));

            final float off = 200;
            List<ColoredParams> spawnParamsList = new ArrayList<>();
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player4, 0, 0, width-off, off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player2, 0, 0, width-off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player3, 0, 0, off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player1, 0, 0, off, off,0));

            setupList.add(new Setup(spawnParamsList, obstacleList, breakableObstacleList, plateList));
        }
        // Setup 2.
        {
            List<Params> plateList = new ArrayList<>();
            plateList.add(new Params(300, 200));

            List<Params> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle(500, 500, 80, 1, 5, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1000, 200, 80, 5, 1, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(1300, 800, 80, 1, 3, 0));
            obstacleList.addAll(Obstacle.rectangleObstacle(200, 600, 80, 1, 5, 0));

            List<Params> breakableObstacleList = new ArrayList<>();

            final float off = 300;
            List<ColoredParams> spawnParamsList = new ArrayList<>();
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player4, 0, 0, width-off, off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player2, 0, 0, width-off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player3, 0, 0, off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player1, 0, 0, off, off,0));

            setupList.add(new Setup(spawnParamsList, obstacleList, breakableObstacleList, plateList));
        }
        // Setup 3.
        {
            List<Params> plateList = new ArrayList<>();
            plateList.add(new Params(300, 200));

            List<Params> obstacleList = new ArrayList<>();
            obstacleList.addAll(Obstacle.rectangleObstacle((float) width /2, (float) height /2, 500, 1, 1, 0));

            List<Params> breakableObstacleList = new ArrayList<>();

            final float off = 200;
            List<ColoredParams> spawnParamsList = new ArrayList<>();
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player4, 0, 0, width-off, off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player2, 0, 0, width-off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player3, 0, 0, off, height-off,0));
            spawnParamsList.add(new ColoredParams(ModelPlayer.Player1, 0, 0, off, off,0));

            setupList.add(new Setup(spawnParamsList, obstacleList, breakableObstacleList, plateList));
        }
    }
    List<Params> obstacleList;
    List<Params> plateList;
    List<ColoredParams> spawnParamsList;
    List<Params> breakableObstacleList;

    public Setup(List<ColoredParams> spawnParamsList, List<Params> obstacleList, List<Params> breakableObstacleList, List<Params> plateList){
        this.spawnParamsList = spawnParamsList;
        this.obstacleList = obstacleList;
        this.breakableObstacleList = breakableObstacleList;
        this.plateList = plateList;
        if(spawnParamsList==null) this.spawnParamsList = new ArrayList<>();
        if(obstacleList==null) this.obstacleList = new ArrayList<>();
        if(breakableObstacleList==null) this.breakableObstacleList = new ArrayList<>();
        if(plateList==null) this.plateList = new ArrayList<>();
    }

    public List<Params> getObstacleList(){
        return obstacleList;
    }
    public List<Params> getBreakableObstacleList(){
        return breakableObstacleList;
    }
    public List<Params> getPlateList() { return plateList; }
    public List<ColoredParams> getSpawnParamsList(){
        return spawnParamsList;
    }
    public static List<Setup> getSetupList()
    {
        return setupList;
    }
}
