package com.pao.game.view.GameScreen.Drawing;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.communication.Descriptions.ConcreteDescription.TankDescription;
import com.pao.game.model.ModelPlayer;
import com.pao.game.view.Textures;

public class MagazineView {
    float x;
    float y;
    int bullets;
    int plates;
    boolean dynamite;
    ModelPlayer color;
    public MagazineView(TankDescription tank){
        x = 100;
        y = 50;
        if(tank.getColor() == ModelPlayer.Player1) {
            x = 227.5f;
        }
        if(tank.getColor() == ModelPlayer.Player2) {
            x = 1692.5f;
        }
        if(tank.getColor() == ModelPlayer.Player3) {
            x = 682.5f;
        }
        if(tank.getColor() == ModelPlayer.Player4) {
            x = 1237.5f;
        }
        bullets = tank.getBullets();
        plates = tank.getPlates();
        dynamite = tank.getDynamite();
        color = tank.getColor();
    }
    public float getX(){return x;}
    public float getY(){return y;}
    public int getBullets(){return bullets;}
    public int getPlates(){return plates;}
    public boolean getDynamite(){return dynamite;}
    public ModelPlayer getColor(){return color;}
}
