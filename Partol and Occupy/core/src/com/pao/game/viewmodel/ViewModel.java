package com.pao.game.viewmodel;

import com.pao.game.model.Board;
import com.pao.game.model.Bullet;
import com.pao.game.model.Tank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.pao.game.viewmodel.Move.U;
import static com.pao.game.viewmodel.Move.L;
import static com.pao.game.viewmodel.Move.R;
import static com.pao.game.viewmodel.Move.B;

public class ViewModel {
    Board board;
    public void setMove(Color color,Move move, boolean state)
    {
        Iterator<Tank> itr = board.getTankList().iterator();
        while(itr.hasNext())
        {
            Tank tank = itr.next();
            if(tank.getColor() == color){
                if(move == U) tank.setMoveForwardState(state);
                if(move == L) tank.setMoveLeftState(state);
                if(move == R) tank.setMoveRightState(state);
                if(move == B) tank.setMoveBackwardsState(state);
            }
        }
    }
    public List<ColoredParams> getTanks()
    {
        List<ColoredParams> tanksParamsList = new ArrayList<>();
        Iterator<Tank> Itr = board.getTankList().iterator();
        while(Itr.hasNext())
        {
            Tank tank = Itr.next();
            tanksParamsList.add(new ColoredParams(tank.getColor(),tank.getWidth(),tank.getHeight(),tank.getX(),tank.getY(),tank.getRotation()));
        }
        return tanksParamsList;
    }
    public List<ColoredParams> getBullets()
    {
        List<ColoredParams> bulletsParamsList = new ArrayList<>();
        Iterator<Bullet> Itr = board.getBulletList().iterator();
        while(Itr.hasNext())
        {
            Bullet bullet = Itr.next();
            bulletsParamsList.add(new ColoredParams(bullet.getColor(),bullet.getWidth(),bullet.getHeight(),bullet.getX(),bullet.getY(),bullet.getRotation()));
        }
    }

}
