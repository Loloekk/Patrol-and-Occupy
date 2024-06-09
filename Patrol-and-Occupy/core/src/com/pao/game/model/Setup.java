package com.pao.game.model;

import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.Obstacles.BrakeObstacle.BrakeObstacle;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Obstacles.BrakeObstacle.BrakeObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle.StoneObstacleFactory;
import com.pao.game.model.GameObject.Others.Plate.PlateCreatingParams;
import com.pao.game.model.GameObject.Others.Spawn.SpawnCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.TankCreatingParams;

import java.util.ArrayList;
import java.util.List;

public class Setup {
    static int width = ModelSettings.getWidth();
    static int height = ModelSettings.getHeight();
    private static final List<List<CreatingParams>> setupList = new ArrayList<>();
    static{
        // Setup 0. (Empty)
        {
            List<CreatingParams> objectList = new ArrayList<>();

            final float off = 200;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));
            setupList.add(objectList);
        }
        // Setup 1.
        {
            List<CreatingParams> objectList = new ArrayList<>();
            final float off = 200;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));

            objectList.add(new PlateCreatingParams().setX(300).setY(200));
            objectList.add(new PlateCreatingParams().setX(1000).setY(500));
            objectList.add(new PlateCreatingParams().setX(1500).setY(300));
            objectList.add(new PlateCreatingParams().setX(450).setY(900));

            objectList.addAll(Obstacle.rectangleObstacle(1000, 200, 50, 1, 5, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 5, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0,new StoneObstacleFactory()));

            objectList.addAll(BrakeObstacle.rectangleObstacle(400, 700, 50, 2, 6, 30,new BrakeObstacleFactory()));
            objectList.addAll(BrakeObstacle.rectangleObstacle(400, 400, 50, 4, 1, 0,new BrakeObstacleFactory()));




            setupList.add(objectList);
        }
        // Setup 2.
        {
            List<CreatingParams> objectList = new ArrayList<>();
            final float off = 300;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));

            objectList.add(new PlateCreatingParams().setX(300).setY(200));

            objectList.addAll(Obstacle.rectangleObstacle(500, 500, 80, 1, 5, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1000, 200, 80, 5, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1300, 800, 80, 1, 3, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(200, 600, 80, 1, 5, 0,new StoneObstacleFactory()));

            setupList.add(objectList);
        }
        // Setup 3.
        {

            List<CreatingParams> objectList = new ArrayList<>();
            final float off = 200;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width-off).setY(off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width-off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(off).setY(height-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(off).setY(off));

            objectList.add(new PlateCreatingParams().setX(300).setY(200));
            objectList.addAll(Obstacle.rectangleObstacle((float) width /2, (float) height /2, 500, 1, 1, 0,new StoneObstacleFactory()));

            setupList.add(objectList);
        }
    }
    public static List<CreatingParams> getObjectList(int n){
        return setupList.get(n);
    }
}
