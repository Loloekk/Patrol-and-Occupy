package com.pao.game.model.GameObject.Others.Tank;

import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Bodies.PolyContainsPoly;
import com.pao.game.model.GameObject.Explosions.BulletExplosion.BulletExplosion;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Controler.Controler;
import com.pao.game.model.GameObject.Others.Tank.Controler.Controler5Buttons;
import com.pao.game.model.GameObject.Others.Tank.Controler.ControlerOneButton;
import java.util.List;

import static com.pao.game.constants.Box2dConstants.PPM;

public class Tank extends BodyGameObject {
    ModelPlayer color;
    Board board;
    PlayerStatistics playerStatistics;
    Spawn spawn;
    Controler controler;
    boolean isAlive;
    float width;
    float height;
    public Tank(TankCreatingParams TCP, Board board) {
        super(TCP,board.getWorld());
        body.setUserData(this);
        color = TCP.getColor();
        this.board = board;
        isAlive = true;
        playerStatistics = new PlayerStatistics();
        width = TCP.getRealWidth();
        height = TCP.getRealHeight();
        spawn = null;
        for(BodyGameObject obj : board.getBodyObjects()) if(obj instanceof Spawn && ((Spawn) obj).getColor() == this.color)
        {
            spawn = (Spawn) obj;
            ((Spawn)obj).setTank(this);
            break;
        }
        if(!ModelSettings.getControl()){
            controler = new Controler5Buttons(this);
        }
        else {
            controler = new ControlerOneButton(this);
        }

    }




    public void setMove(Move move, boolean state){
        controler.setMove(move,state);
    }
    public void revive() {
        isAlive = true;
    }
    private boolean isInSpawn(){
        if(spawn == null) return false;
        return PolyContainsPoly.isPolyInPoly(body,spawn.body);
    }
    public void takeDamage(BodyGameObject killer)
    {
        if(!isAlive || isInSpawn()) return;

        if(!(killer instanceof ExplosionCircle)) return;

        if(killer instanceof BulletExplosion && ((BulletExplosion) killer).getColor() == color) return;

        playerStatistics.incrementDeadNumber();
        board.getTank(((ExplosionCircle) killer).getColor()).getStatistics().incrementKillNumber();
        isAlive = false;
    }
    public void setPosition(float x, float y) { body.setTransform(x/PPM,y/PPM,0);}
    public ModelPlayer getColor() {
        return color;
    }
    public PlayerStatistics getStatistics()
    {
        return playerStatistics;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public boolean getIsActive(){return true;}
    public Board getBoard(){return board;}
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public void update(float time) {
        controler.update(time);
    }
    @Override
    public List<ObjectDescription> getDescription()
    {
        return List.of((
                new TankDescription())
                        .setIsAlive(getIsAlive())
                        .setColor(getColor())
                        .setX(getX())
                        .setY(getY())
                        .setWidth(getWidth())
                        .setHeight(getHeight())
                        .setRotation(getRotation()),
                controler.getMagazine().getDescription()
                        .setPlates(playerStatistics.getNumberOfPlates())
                        .setColor(getColor()));
    }
}