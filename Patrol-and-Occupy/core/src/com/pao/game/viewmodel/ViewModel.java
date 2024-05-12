package com.pao.game.viewmodel;

import com.pao.game.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.pao.game.viewmodel.Move.F;
import static com.pao.game.viewmodel.Move.L;
import static com.pao.game.viewmodel.Move.R;
import static com.pao.game.viewmodel.Move.B;

public class ViewModel {
    Board board;
    public ViewModel(int width, int height, int n)
    {
        board = new SimpleBoard(width,height, Color.getColorList(n));
    }
    public void setMove(Color color,Move move, boolean state)
    {
        board.setmove(color,move,state);
    }
    public void shoot(Color color){board.shoot(color);}
    public List<ColoredParams> getTanks()
    {
        List<ColoredParams> tanksParamsList = new ArrayList<>();
        Iterator<Tank> Itr = board.getTankList().iterator();
        while(Itr.hasNext())
        {
            Tank tank = Itr.next();
            tanksParamsList.add(new ColoredParams(tank.getIsAlive() ? tank.getColor() : null,tank.getWidth(),tank.getHeight(),tank.getX(),tank.getY(),tank.getRotation()));
        }
        return tanksParamsList;
    }
    public List<ColoredParams> getBullets() {
        List<ColoredParams> bulletsParamsList = new ArrayList<>();
        Iterator<Bullet> Itr = board.getBulletList().iterator();
        while (Itr.hasNext()) {
            Bullet bullet = Itr.next();
            bulletsParamsList.add(new ColoredParams(bullet.getColor(), bullet.getWidth(), bullet.getHeight(), bullet.getX(), bullet.getY(), bullet.getRotation()));
        }
        return bulletsParamsList;
    }
    public List<Params> getObstacles() {
        List<Params> obstaclesParamsList = new ArrayList<>();
        Iterator<Obstacle> Itr = board.getObstacleList().iterator();
        while(Itr.hasNext())
        {
            Obstacle obstacle = Itr.next();
            obstaclesParamsList.add(new Params(obstacle.getWidth(), obstacle.getHeight(), obstacle.getX(), obstacle.getY(), 0));
        }
        return obstaclesParamsList;
    }
    public void update(float time) {
        board.update(time);
    }
}