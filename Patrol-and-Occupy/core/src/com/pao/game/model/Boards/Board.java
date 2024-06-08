package com.pao.game.model.Boards;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.Others.Tank.Tank;

import java.util.List;

public interface Board {
    void update(float t);
    float getRemainingTime();
    void setMove(ModelPlayer color, Move move, boolean value);
    List<BodyGameObject> getBodyObjects();
    List<Tank> getTankList();
    Tank getTank(ModelPlayer color);
    World getWorld();
    void addObject(BodyGameObject obj);
    void addObjectToCreate(CreatingParams obj);
}