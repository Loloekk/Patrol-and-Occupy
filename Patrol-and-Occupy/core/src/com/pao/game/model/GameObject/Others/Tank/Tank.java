package com.pao.game.model.GameObject.Others.Tank;

import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.communication.Move;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Bodies.PolyContainsPoly;
import com.pao.game.model.GameObject.Explosions.BulletExplosion.BulletExplosion;
import com.pao.game.model.GameObject.Explosions.BulletShoot.BulletShoot;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Controler.Controler;
import com.pao.game.model.GameObject.Others.Tank.Controler.Controler5Buttons;
import com.pao.game.model.GameObject.Others.Tank.Controler.ControlerOneButton;

import static com.pao.game.model.Constants.PPM;

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
        this.color = TCP.getColor();
        this.board = board;
        this.isAlive = true;
        this.playerStatistics = new PlayerStatistics();
        width = TCP.getRealWidth();
        height = TCP.getRealHeight();
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
        return PolyContainsPoly.isPolyInPoly(body,spawn.body);
    }
    public void takeDamage(BodyGameObject killer)
    {
        if(!isAlive || isInSpawn()) return;

        if(!(killer instanceof ExplosionCircle)) return;

        if(killer instanceof BulletExplosion && ((BulletExplosion) killer).getColor() == color) return;
        if(killer instanceof BulletShoot) return;
        playerStatistics.incrementDeadNumber();
        board.getTank(((ExplosionCircle) killer).getColor()).getStatistics().incrementKillNumber();
        isAlive = false;
    }
    public void setPosition(float x, float y) { body.setTransform(x/PPM,y/PPM, 0);}
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
    public void setSpawn(Spawn spawn){this.spawn=spawn;}
    public void update(float time) {
        controler.update(time);
    }
    @Override
    public TankDescription getDescription()
    {
        return (TankDescription) (new TankDescription())
                .setBullets(controler.getMagazine().getQuantity())
                .setPlates(playerStatistics.getNumberOfPlates())
                .setIsAlive(getIsAlive())
                .setDynamite(controler.getMagazine().hasDynamite())
                .setColor(getColor())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}