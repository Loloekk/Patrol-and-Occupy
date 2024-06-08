package com.pao.game.model.GameObject.Others.Spawn;

import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelPlayer;

public class Spawn extends BodyGameObject {
    float COOLDOWN = 5;
    ModelPlayer color;
    Tank tank;
    float cooldown=0;
    public Spawn(SpawnCreatingParams SCP, Board board) {
        super(SCP,board.getWorld());
        tank = (Tank) SCP.getTankCreatingParams().create(board);
        tank.setSpawn(this);
        this.color = SCP.getColor();
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
            tank.setPosition(getX(),getY());
            tank.revive();
        }
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
}
