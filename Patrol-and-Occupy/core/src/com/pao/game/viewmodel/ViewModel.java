package com.pao.game.viewmodel;

import com.badlogic.gdx.utils.TimeUtils;
import com.pao.game.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.pao.game.viewmodel.Move.F;
import static com.pao.game.viewmodel.Move.L;
import static com.pao.game.viewmodel.Move.R;
import static com.pao.game.viewmodel.Move.B;

public class ViewModel implements Runnable{
    Board board;
    public ViewModel(int width, int height, int n)
    {
        board = new SimpleBoard(width,height, MyColor.getColorList(n), Setup.setupList.get(1));
    }
    public void setMove(MyColor color,Move move, boolean state)
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
    public float getRemainingTime()
    {
        return board.getRemainingTime();
    }
    public void run()
    {
        double lastTimeUpdate = ((double) TimeUtils.millis())/1000d;
        while(true){
            //System.out.println(lastTimeUpdate);
            double currentTime = ((double) TimeUtils.millis())/1000d;
            board.update((float)(currentTime - lastTimeUpdate));
            lastTimeUpdate = currentTime;

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}