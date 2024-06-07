package com.pao.game.model.GameObject;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.ModelPlayer;

public class Plate extends BodyGameObject {
    static final int width = 80;
    static final int height = 80;
    ModelPlayer color;
    Board board;
    public Plate(float x, float y, Board board) {
        super(x, y, 0f, 0f, 0f, BodyDef.BodyType.StaticBody, board.getWorld(), 1f, true);
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
}
