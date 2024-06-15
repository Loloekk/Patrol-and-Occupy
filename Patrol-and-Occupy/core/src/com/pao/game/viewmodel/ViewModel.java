package com.pao.game.viewmodel;

import com.pao.game.communication.*;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.Boards.SimpleBoard;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Others.Tank.Tank;

import java.util.ArrayList;
import java.util.List;

public class ViewModel{
    Board board;
    GlobalStatistics globalStatistics;
    public ViewModel()
    {
        board = new SimpleBoard();
        globalStatistics = new GlobalStatistics(this);
    }

    public List<ObjectDescription> getObjectDescriptionList()
    {
        List<ObjectDescription> objectDescriptionList = new ArrayList<>();
        for(BodyGameObject obj : board.getBodyObjects())
        {
            List<ObjectDescription> desc = obj.getDescription();
            if(desc != null) objectDescriptionList.addAll(desc);
        }
        return objectDescriptionList;
    }
    public void setMove(ModelPlayer color, Move move, boolean state)
    {
        board.setMove(color,move,state);
    }
    public Tank getTank(ModelPlayer color){
        return board.getTank(color);
    }
    public float getRemainingTime()
    {
        return board.getRemainingTime();
    }
    public GlobalStatistics getStatistics() {
        return globalStatistics;
    }
    public void update(float time) {
        board.update(time);
    }
}