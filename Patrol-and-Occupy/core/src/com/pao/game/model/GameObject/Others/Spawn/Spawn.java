package com.pao.game.model.GameObject.Others.Spawn;

import com.pao.game.communication.Descriptions.ConcreteDescription.SpawnDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelPlayer;
import com.pao.game.model.ModelSettings;
import java.util.List;

public class Spawn extends BodyGameObject {
    float COOLDOWN = ModelSettings.getRespawnCooldown();
    ModelPlayer color;
    Tank tank;
    float cooldown=0;
    public Spawn(SpawnCreatingParams SCP, Board board) {
        super(SCP,board.getWorld());
        this.color = SCP.getColor();
        tank = null;
        body.setUserData(this);
    }
    @Override
    public void update(float time)
    {
        if(!tank.getIsAlive()){
            cooldown+=time;
        }
        if(cooldown > COOLDOWN)
        {
            cooldown = 0;
            if(tank == null) return;
            tank.setPosition(getX(),getY(), tank.getRotation());
            tank.revive();
        }
    }
    public void setTank(Tank tank){
        this.tank = tank;
    }
    public ModelPlayer getColor() {
        return color;
    }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((new SpawnDescription())
                .setColor(color)
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation()));
    }
}
