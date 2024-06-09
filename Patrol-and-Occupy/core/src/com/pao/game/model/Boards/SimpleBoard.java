package com.pao.game.model.Boards;

import java.util.*;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.CreatingParams.CreatingParams;
import com.pao.game.model.GameObject.Others.Spawn.SpawnCreatingParams;
import com.pao.game.model.GameObject.Others.Tank.Clock;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.GameObject.Others.Tank.TankCreatingParams;

import static com.pao.game.model.Constants.*;

public class SimpleBoard implements Board {
    List<BodyGameObject> bodyObjects = new ArrayList<>();
    List<CreatingParams> bodyObjectsToCreate = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    int width, height;
    Clock clock;
    World world;

    private SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        world = MyWorld.createMyWorld();
        clock = new Clock();
    }
    public SimpleBoard(){
        this(ModelSettings.getWidth(), ModelSettings.getHeight());
        // Add players tanks
        List<CreatingParams> toCreate = Setup.getObjectList(ModelSettings.getMap());
        for(CreatingParams cp : toCreate)
        {
            if(cp instanceof SpawnCreatingParams || cp instanceof TankCreatingParams){
                for(ModelPlayer color : ModelPlayer.getColorList(ModelSettings.getNumberOfPlayers())) {
                    if(cp.getColor() == color){
                        bodyObjectsToCreate.add(cp);
                    }
                }
            }
            else {
                bodyObjectsToCreate.add(cp);
            }
        }
    }
    public void update(float time) {
        for(CreatingParams CP: bodyObjectsToCreate){
            BodyGameObject object = CP.create(this);
        }
        bodyObjectsToCreate.clear();
        world.step((time), VELOCITY_ITERATION, POSITION_ITERATION);

        clock.update(time);
        // Update tanks
        for(BodyGameObject obj : new ArrayList<>(bodyObjects))
        {
            if(obj.getIsActive()){
                obj.update(time);
            }
        }
        List<BodyGameObject> objectToDestroy = new ArrayList<>();
        for(BodyGameObject obj : bodyObjects)
        {
            if(!obj.getIsActive()){
                objectToDestroy.add(obj);
                world.destroyBody(obj.getBody());
            }
        }
        bodyObjects.removeAll(objectToDestroy);

    }
    @Override
    public float getRemainingTime() {
        return clock.getRemainingTime();
    }
    public void setMove(ModelPlayer color, Move move, boolean state) {
        Tank tank = null;
        for (Tank t : tankList) {
            if (color == t.getColor()) {
                tank = t;
                break;
            }
        }
        if(tank == null){
            return;
        }
        tank.setMove(move,state);
    }
    public List<BodyGameObject> getBodyObjects() { return bodyObjects; }

    @Override
    public List<Tank> getTankList() {
        return tankList;
    }

    public Tank getTank(ModelPlayer color){
        return tankList.stream()
                .filter(tank -> tank.getColor() == color)
                .findFirst()
                .orElse(null);
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public World getWorld(){return world; }
    public void addObject(BodyGameObject obj) {
        if(obj == null) return;
        bodyObjects.add(obj);
        if(obj instanceof Tank){
            tankList.add((Tank) obj);
        }

    }
    public void addObjectToCreate(CreatingParams CP){
        bodyObjectsToCreate.add(CP);
    }
}