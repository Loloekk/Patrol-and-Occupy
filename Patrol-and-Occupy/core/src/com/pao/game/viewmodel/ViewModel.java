package com.pao.game.viewmodel;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.ColoredParams;
import com.pao.game.communication.Move;
import com.pao.game.communication.Params;
import com.pao.game.communication.TankParams;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.Boards.SimpleBoard;
import com.pao.game.model.MultiContactListener.BulletContactListener;
import com.pao.game.model.MultiContactListener.MultiContactListener;
import com.pao.game.model.MultiContactListener.TankBreakableObstacleContactListener;
import com.pao.game.model.MultiContactListener.TankSpawnContactListener;

import java.util.ArrayList;
import java.util.List;

import static com.pao.game.model.Constants.*;


public class ViewModel{
    Board board;
    World world;
    MultiContactListener multiContactListener;
    GlobalStatistics globalStatistics;
    public ViewModel()
    {
        world = new World(GRAVITY, true);
        board = new SimpleBoard(world);
        multiContactListener = new MultiContactListener();
        world.setContactListener(multiContactListener);
        multiContactListener.addListener(new TankBreakableObstacleContactListener(board));
        multiContactListener.addListener(new TankSpawnContactListener());
        multiContactListener.addListener(new BulletContactListener(board));
        BodyCreator.setEdges(0,0, ModelSettings.getWidth(), ModelSettings.getHeight(), world);
        globalStatistics = new GlobalStatistics(this);
    }

    public void setMove(ModelPlayer color, Move move, boolean state)
    {
        board.setmove(color,move,state);
    }
    public List<TankParams> getTanks()
    {
        List<TankParams> tanksParamsList = new ArrayList<>();
        for(Tank tank : board.getTankList())
        {
            tanksParamsList.add(new TankParams(tank.getColor(),
                    tank.getWidth(),tank.getHeight(),tank.getX(),tank.getY(),
                    tank.getRotation(),tank.getBullets(),tank.getStatistics().getNumberOfPlates(),tank.getIsAlive()));
        }
        return tanksParamsList;
    }
    public Tank getTank(ModelPlayer color){
        return board.getTank(color);
    }
    public List<ColoredParams> getBullets() {
        List<ColoredParams> bulletsParamsList = new ArrayList<>();
        for(Bullet bullet : board.getBulletList())
        {
            bulletsParamsList.add(new ColoredParams(bullet.getColor(), bullet.getWidth(), bullet.getHeight(), bullet.getX(), bullet.getY(), bullet.getRotation()));
        }
        return bulletsParamsList;
    }
    public List<Params> getObstacles() {
        List<Params> obstaclesParamsList = new ArrayList<>();
        for(Obstacle obstacle : board.getObstacleList())
        {
            obstaclesParamsList.add(new Params(obstacle.getWidth(), obstacle.getHeight(), obstacle.getX(), obstacle.getY(), obstacle.getRotation()));
        }
        return obstaclesParamsList;
    }
    public List<Params> getBreakableObstacles() {
        List<Params> breakableObstaclesParamsList = new ArrayList<>();
        for(BreakableObstacle breakableObstacle : board.getBreakableObstacleList())
        {
            breakableObstaclesParamsList.add(new Params(breakableObstacle.getWidth(), breakableObstacle.getHeight(), breakableObstacle.getX(), breakableObstacle.getY(), breakableObstacle.getRotation()));
        }
        return breakableObstaclesParamsList;
    }

    public List<ColoredParams> getPlates() {
        List<ColoredParams> platesParamsList = new ArrayList<>();
        for(Plate plate : board.getPlateList())
        {
            platesParamsList.add(new ColoredParams(plate.getColor(), plate.getWidth(), plate.getHeight(), plate.getX(), plate.getY(), plate.getRotation()));
        }
        return platesParamsList;
    }
    public List<Params> getDynamites() {
        List<Params> dynamitesParamsList = new ArrayList<>();
        for(Dynamite dynamite : board.getDynamiteList())
        {
            dynamitesParamsList.add(new Params(dynamite.getWidth(), dynamite.getHeight(), dynamite.getX(), dynamite.getY(), dynamite.getRotation()));
        }
        return dynamitesParamsList;
    }
    public List<ColoredParams> getSpawns(){
        List<ColoredParams> spawnsParamsList = new ArrayList<>();
        for(Spawn spawn : board.getSpawnList())
        {
            spawnsParamsList.add(new ColoredParams(spawn.getColor(), spawn.getWidth(), spawn.getHeight(), spawn.getX(), spawn.getY(), spawn.getRotation()));
        }
        return spawnsParamsList;
    }
    public float getRemainingTime()
    {
        return board.getRemainingTime();
    }
    public GlobalStatistics getStatistics() {
        return globalStatistics;
    }
    public void update(float time) {
        world.step((float)(time), VELOCITY_ITERATION, POSITION_ITERATION);
        board.update(time);
    }
}