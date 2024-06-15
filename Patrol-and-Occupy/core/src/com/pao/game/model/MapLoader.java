package com.pao.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pao.game.Constants.ConfigLoader;
import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.Obstacles.BrakeObstacle.BrakeObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.AbstractObstacle.Obstacle;
import com.pao.game.model.GameObject.Obstacles.BricksObstacle.BricksObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl.HedgehogsObstacleFactory;
import com.pao.game.model.GameObject.Obstacles.StoneObstacle.StoneObstacleFactory;
import com.pao.game.model.GameObject.Others.Plate.PlateCreatingParams;
import com.pao.game.model.GameObject.Others.Spawn.SpawnCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.TankCreatingParams;

public class MapLoader {
    static final Map<Integer, List<CreatingParams>> map = new HashMap<>();

    public static List<CreatingParams> getMap(int n)
    {
        if(!map.containsKey(n))
        {
            List<CreatingParams> list = new ArrayList<>();
            ConfigLoader loader;
            try {
                loader = new ConfigLoader("assets/Maps/map" + n + ".properties");
            } catch(Exception e){
                loader = new ConfigLoader("assets/Maps/map0.properties");
            }
                for(ModelPlayer pla : ModelPlayer.getAllColorList())
            {
                list.add(new SpawnCreatingParams().setColor(pla).
                        setX(loader.getFloatProperty("Spawn."+pla+".X")).
                        setY(loader.getFloatProperty("Spawn."+pla+".Y")).
                        setRotation(loader.getFloatProperty("Spawn."+pla+".Rotation")));
                list.add(new TankCreatingParams().setColor(pla).
                        setX(loader.getFloatProperty("Tank."+pla+".X")).
                        setY(loader.getFloatProperty("Tank."+pla+".Y")).
                        setRotation(loader.getFloatProperty("Tank."+pla+".Rotation")));
            }
            int plates = loader.getIntProperty("Number.Of.Plates");
            for(int i = 1; i<= plates ;i ++)
            {
                list.add(new PlateCreatingParams().
                        setX(loader.getFloatProperty("Plate"+i+".X")).
                        setY(loader.getFloatProperty("Plate"+i+".Y")).
                        setRotation(loader.getFloatProperty("Plate"+i+".Rotation")));
            }
            int stones = loader.getIntProperty("Number.Of.Stones");
            for(int i = 1; i<= stones ;i ++)
            {
                list.addAll(Obstacle.rectangleObstacle(loader.getFloatProperty("Stone"+i+".X"),
                        loader.getFloatProperty("Stone"+i+".Y"),
                        loader.getFloatProperty("Stone"+i+".Side"),
                        loader.getIntProperty("Stone"+i+".Number.Of.Rows"),
                        loader.getIntProperty("Stone"+i+".Number.Of.Columns"),
                        loader.getFloatProperty("Stone"+i+".Degree"),
                        new StoneObstacleFactory()));
            }
            int bricks = loader.getIntProperty("Number.Of.Bricks");
            for(int i = 1; i<= bricks ;i ++)
            {
                list.addAll(Obstacle.rectangleObstacle(loader.getFloatProperty("Bricks"+i+".X"),
                        loader.getFloatProperty("Bricks"+i+".Y"),
                        loader.getFloatProperty("Bricks"+i+".Side"),
                        loader.getIntProperty("Bricks"+i+".Number.Of.Rows"),
                        loader.getIntProperty("Bricks"+i+".Number.Of.Columns"),
                        loader.getFloatProperty("Bricks"+i+".Degree"),
                        new BricksObstacleFactory()));
            }
            int hedgehogs = loader.getIntProperty("Number.Of.Hedgehogs");
            for(int i = 1; i<= hedgehogs ;i ++)
            {
                list.addAll(Obstacle.rectangleObstacle(loader.getFloatProperty("Hedgehogs"+i+".X"),
                        loader.getFloatProperty("Hedgehogs"+i+".Y"),
                        loader.getFloatProperty("Hedgehogs"+i+".Side"),
                        loader.getIntProperty("Hedgehogs"+i+".Number.Of.Rows"),
                        loader.getIntProperty("Hedgehogs"+i+".Number.Of.Columns"),
                        loader.getFloatProperty("Hedgehogs"+i+".Degree"),
                        new HedgehogsObstacleFactory()));
            }
            int brakes = loader.getIntProperty("Number.Of.Brakes");
            for(int i = 1; i<= brakes ;i ++)
            {
                list.addAll(Obstacle.rectangleObstacle(loader.getFloatProperty("Brakes"+i+".X"),
                        loader.getFloatProperty("Brakes"+i+".Y"),
                        loader.getFloatProperty("Brakes"+i+".Side"),
                        loader.getIntProperty("Brakes"+i+".Number.Of.Rows"),
                        loader.getIntProperty("Brakes"+i+".Number.Of.Columns"),
                        loader.getFloatProperty("Brakes"+i+".Degree"),
                        new BrakeObstacleFactory()));
            }
            map.put(n,list);
        }
        return map.get(n);
    }


}
