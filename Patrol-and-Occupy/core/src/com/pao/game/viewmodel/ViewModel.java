package com.pao.game.viewmodel;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.*;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.Boards.SimpleBoard;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Others.Bullet.Bullet;
import com.pao.game.model.GameObject.Others.Dynamite.Dynamite;
import com.pao.game.model.GameObject.Explosions.BulletExplosion.BulletExplosion;
import com.pao.game.model.GameObject.Explosions.BulletShoot.BulletShoot;
import com.pao.game.model.GameObject.Explosions.DynamiteExplosion.DynamiteExplosion;
import com.pao.game.model.GameObject.Obstacles.BreakableObstacle.BreakableObstacle;
import com.pao.game.model.GameObject.Obstacles.UnbreakableObstacle.UnbreakableObstacle;
import com.pao.game.model.GameObject.Others.Plate.Plate;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.MultiContactListener.MultiContactListener;

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
            ObjectDescription desc = obj.getDescription();
            if(desc != null) objectDescriptionList.add(desc);
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