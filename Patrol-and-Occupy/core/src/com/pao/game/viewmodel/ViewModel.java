package com.pao.game.viewmodel;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.Communication.ColoredParams;
import com.pao.game.Communication.Move;
import com.pao.game.Communication.Params;
import com.pao.game.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.pao.game.model.Constants.*;


public class ViewModel{
    Board board;
    World world;
    ModelSettings settings;
    public ViewModel(EditSettings ES)
    {
        settings = ES.getSettings();
        world = new World(GRAVITY, true);
        BodyCreator.setEdges(0,0, settings.getWidth(), settings.getHeight(), world);
        board = new SimpleBoard(settings, world);
    }

    public void setMove(ModelPlayer color, Move move, boolean state)
    {
        board.setmove(color,move,state);
    }
    public List<ColoredParams> getTanks()
    {
        List<ColoredParams> tanksParamsList = new ArrayList<>();
        for(Tank tank : board.getTankList())
        {
            tanksParamsList.add(new ColoredParams(tank.getIsAlive() ? tank.getColor() : null,tank.getWidth(),tank.getHeight(),tank.getX(),tank.getY(),tank.getRotation()));
        }
        return tanksParamsList;
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
        //System.out.println("Size1:" + board.getDynamiteList().size());
        //System.out.println("Size2:" + dynamitesParamsList.size());
        return dynamitesParamsList;
    }
    public float getRemainingTime()
    {
        return board.getRemainingTime();
    }
    public void update(float time) {
        world.step((float)(time), VELOCITY_ITERATION, POSITION_ITERATION);
        board.update(time);
    }
}