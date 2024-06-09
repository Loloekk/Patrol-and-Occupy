package com.pao.game.model.GameObject.Others.Plate;

import com.pao.game.communication.Descriptions.ConcreteDescription.PlateDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelPlayer;

public class Plate extends BodyGameObject {
    static final int width = 80;
    static final int height = 80;
    ModelPlayer color;
    Board board;
    public Plate(PlateCreatingParams PCP, Board board) {
        super(PCP,board.getWorld());
        body.setUserData(this);
        this.color = null;
        this.board = board;
    }
    public void setColor(ModelPlayer color) { this.color = color; }
    public ModelPlayer getColor() { return color; }
    @Override
    public void takeDamage(BodyGameObject killer)
    {
        if(!(killer instanceof Tank)) return;
        if(!((Tank) killer).getIsAlive()) return;
        if(color != null) (board.getTank(color)).getStatistics().decrementNumberOfPlates();
        color = ((Tank) killer).getColor();
        if(color != null) (board.getTank(color)).getStatistics().incrementNumberOfPlates();
    }
    @Override
    public float getWidth() {
        return width;
    }
    @Override
    public float getHeight() {
        return height;
    }
    @Override
    public PlateDescription getDescription()
    {
        return (PlateDescription) (new PlateDescription())
                .setColor(getColor())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}
