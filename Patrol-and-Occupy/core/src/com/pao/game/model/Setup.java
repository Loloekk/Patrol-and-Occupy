package com.pao.game.model;

import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.Obstacles.BrakeObstacle.BrakeObstacle;
import com.pao.game.model.GameObject.Obstacles.BrakeObstacle.BrakeObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Obstacles.BricksObstacle.BricksObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.StoneObstacle.StoneObstacleFactory;
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

            objectList.addAll(Obstacle.rectangleObstacle(400, 700, 50, 2, 6, 30,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(400, 400, 50, 4, 1, 0,new BrakeObstacleFactory()));




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

        // Setup 4.
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

            objectList.add(new PlateCreatingParams().setX(width/2-240).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(width/2-160).setY(height/2+80));
            objectList.add(new PlateCreatingParams().setX(width/2-80).setY(height/2+160));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height/2+240));
            objectList.add(new PlateCreatingParams().setX(width/2+80).setY(height/2+160));
            objectList.add(new PlateCreatingParams().setX(width/2+160).setY(height/2+80));
            objectList.add(new PlateCreatingParams().setX(width/2+240).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(width/2-160).setY(height/2-80));
            objectList.add(new PlateCreatingParams().setX(width/2-80).setY(height/2-160));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height/2-240));
            objectList.add(new PlateCreatingParams().setX(width/2+80).setY(height/2-160));
            objectList.add(new PlateCreatingParams().setX(width/2+160).setY(height/2-80));
            objectList.add(new PlateCreatingParams().setX(width/2-80).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height/2+80));
            objectList.add(new PlateCreatingParams().setX(width/2+80).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height/2-80));


            objectList.addAll(Obstacle.rectangleObstacle(200, 350, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(400, 450, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(350, 700, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1000, 900, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(600, 150, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1600, 300, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1400, 550, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(950, 100, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1100, 230, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1300, 800, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1350, 700, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1500, 200, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(500, 900, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1600, 900, 50, 1, 1, 0,new StoneObstacleFactory()));


            objectList.addAll(Obstacle.rectangleObstacle(200, height/2, 50, 2, 6, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width-200, height/2, 50, 2, 6, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2-250, height/2+250, 50, 2, 6, 45,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2+250, height/2+250, 50, 2, 6, -45,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2-250, height/2-250, 50, 2, 6, -45,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2+250, height/2-250, 50, 2, 6, 45,new BrakeObstacleFactory()));


            setupList.add(objectList);
        }

        // Setup 5.
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


            objectList.add(new PlateCreatingParams().setX(200).setY(500));
            objectList.add(new PlateCreatingParams().setX(500).setY(150));
            objectList.add(new PlateCreatingParams().setX(950).setY(320));
            objectList.add(new PlateCreatingParams().setX(700).setY(800));
            objectList.add(new PlateCreatingParams().setX(1300).setY(500));
            objectList.add(new PlateCreatingParams().setX(1600).setY(400));
            objectList.add(new PlateCreatingParams().setX(1500).setY(850));


            objectList.addAll(Obstacle.rectangleObstacle(1700, 500, 50, 1, 6, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(900, 600, 50, 2, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1200, 300, 50, 2, 5, 45,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(150, 400, 50, 1, 6, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(250, 600, 50, 1, 3, 0,new StoneObstacleFactory()));


            objectList.addAll(Obstacle.rectangleObstacle(450, 700, 50, 4, 4, 30,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(750, 300, 50, 2, 4, 90,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(450, 700, 50, 4, 4, 30,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1100, 700, 50, 2, 5, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1400, 150, 50, 3, 3, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(100, 550, 50, 2, 2, 0,new BrakeObstacleFactory()));


            setupList.add(objectList);
        }

        // Setup 6.
        {
            List<CreatingParams> objectList = new ArrayList<>();
            final float off = 150;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width/2+off).setY(height/2-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width/2+off).setY(height/2+off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(width/2-off).setY(height/2+off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(width/2-off).setY(height/2-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width/2+off).setY(height/2-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width/2+off).setY(height/2+off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(width/2-off).setY(height/2+off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(width/2-off).setY(height/2-off));

            objectList.add(new PlateCreatingParams().setX(width/2).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2-250).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2-500).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2-750).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2+250).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2+500).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2+750).setY(50));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2-250).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2-500).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2-750).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2+250).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2+500).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2+750).setY(height-50));



            objectList.addAll(Obstacle.rectangleObstacle(width/2-375, height-150, 50, 1, 8, 90,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2-375, 150, 50, 1, 8, 90,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2+375, height-150, 50, 1, 8, 90,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2+375, 150, 50, 1, 8, 90,new StoneObstacleFactory()));


            objectList.addAll(Obstacle.rectangleObstacle(53, height/2, 50, 2, 19, 90,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width-53, height/2, 50, 2, 19, 90,new BrakeObstacleFactory()));


            setupList.add(objectList);
        }

        // Setup 7.
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

            objectList.add(new PlateCreatingParams().setX(80).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(240).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(400).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(560).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(720).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(880).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1040).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1200).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1360).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1520).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1680).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(1840).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(450).setY(200));
            objectList.add(new PlateCreatingParams().setX(650).setY(150));
            objectList.add(new PlateCreatingParams().setX(950).setY(180));
            objectList.add(new PlateCreatingParams().setX(1300).setY(200));
            objectList.add(new PlateCreatingParams().setX(1600).setY(170));
            objectList.add(new PlateCreatingParams().setX(470).setY(800));
            objectList.add(new PlateCreatingParams().setX(800).setY(750));
            objectList.add(new PlateCreatingParams().setX(1150).setY(750));
            objectList.add(new PlateCreatingParams().setX(1500).setY(700));

            objectList.addAll(Obstacle.rectangleObstacle(350, 250, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(500, 650, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(650, 750, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(900, 300, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1200, 150, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1350, 800, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1250, 700, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1400, 700, 50, 1, 1, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(1500, 300, 50, 1, 1, 0,new StoneObstacleFactory()));


            objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2+100, 50, 2, 38, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2-100, 50, 2, 38, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, 50, 50, 2, 38, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, height-50, 50, 2, 38, 0,new BrakeObstacleFactory()));


            setupList.add(objectList);
        }

        // Setup 8.
        {
            List<CreatingParams> objectList = new ArrayList<>();
            final float off = 150;
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player4).setX(width/2+off).setY(height/2-off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player2).setX(width/2+off).setY(height/2+off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player3).setX(width/2-off).setY(height/2+off));
            objectList.add(new SpawnCreatingParams().setColor(ModelPlayer.Player1).setX(width/2-off).setY(height/2-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player4).setX(width/2+off).setY(height/2-off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player2).setX(width/2+off).setY(height/2+off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player3).setX(width/2-off).setY(height/2+off));
            objectList.add(new TankCreatingParams().setColor(ModelPlayer.Player1).setX(width/2-off).setY(height/2-off));

            objectList.add(new PlateCreatingParams().setX(50).setY(130));
            objectList.add(new PlateCreatingParams().setX(130).setY(50));
            objectList.add(new PlateCreatingParams().setX(width-50).setY(130));
            objectList.add(new PlateCreatingParams().setX(width-130).setY(50));
            objectList.add(new PlateCreatingParams().setX(width-50).setY(height-130));
            objectList.add(new PlateCreatingParams().setX(width-130).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(50).setY(height-130));
            objectList.add(new PlateCreatingParams().setX(130).setY(height-50));


            objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2, 50, 1, 10, 0,new StoneObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2, 50, 1, 10, 90,new StoneObstacleFactory()));

            objectList.addAll(Obstacle.rectangleObstacle(width/2, 30, 50, 1, 30, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, height-30, 50, 1, 30, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(30, height/2, 50, 1, 11, 90,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width-30, height/2, 50, 1, 11, 90,new BrakeObstacleFactory()));



            setupList.add(objectList);
        }

        // Setup 9.
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

            objectList.add(new PlateCreatingParams().setX(50).setY(130));
            objectList.add(new PlateCreatingParams().setX(130).setY(50));
            objectList.add(new PlateCreatingParams().setX(width-50).setY(130));
            objectList.add(new PlateCreatingParams().setX(width-130).setY(50));
            objectList.add(new PlateCreatingParams().setX(width-50).setY(height-130));
            objectList.add(new PlateCreatingParams().setX(width-130).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(50).setY(height-130));
            objectList.add(new PlateCreatingParams().setX(130).setY(height-50));
            objectList.add(new PlateCreatingParams().setX(width/2-80).setY(height/2+80));
            objectList.add(new PlateCreatingParams().setX(width/2-80).setY(height/2-80));
            objectList.add(new PlateCreatingParams().setX(width/2).setY(height/2));
            objectList.add(new PlateCreatingParams().setX(width/2+80).setY(height/2-80));
            objectList.add(new PlateCreatingParams().setX(width/2+80).setY(height/2+80));


            //objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2, 50, 1, 10, 0,new UnbreakableObstacleFactory()));
            //objectList.addAll(Obstacle.rectangleObstacle(width/2, height/2, 50, 1, 10, 90,new UnbreakableObstacleFactory()));
            //objectList.addAll(Obstacle.rectangleObstacle(650, 750, 50, 1, 1, 0,new UnbreakableObstacleFactory()));


            objectList.addAll(Obstacle.rectangleObstacle(width/2, 30, 50, 1, 30, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width/2, height-30, 50, 1, 30, 0,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(30, height/2, 50, 1, 11, 90,new BrakeObstacleFactory()));
            objectList.addAll(Obstacle.rectangleObstacle(width-30, height/2, 50, 1, 11, 90,new BrakeObstacleFactory()));


            setupList.add(objectList);
        }


    }
    public static List<CreatingParams> getObjectList(int n){
        return setupList.get(n);
    }
}
