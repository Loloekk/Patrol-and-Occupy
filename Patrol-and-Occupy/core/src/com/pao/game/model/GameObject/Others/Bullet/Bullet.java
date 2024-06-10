package com.pao.game.model.GameObject.Others.Bullet;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.pao.game.communication.Descriptions.ConcreteDescription.BulletDescription;
import com.pao.game.model.Boards.Board;
import com.pao.game.Constants.Constants;
import com.pao.game.model.GameObject.Bodies.BodyGameObject;
import com.pao.game.model.GameObject.Explosions.BulletExplosion.BulletExplosionCreatingParams;
import com.pao.game.model.GameObject.Explosions.DynamiteExplosion.DynamiteExplosion;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionCircle;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionRectangle;
import com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl.HedgehogsObstacle;
import com.pao.game.model.GameObject.Others.Plate.Plate;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Tank;
import com.pao.game.model.ModelPlayer;
import com.pao.game.model.ModelSettings;

public class Bullet extends BodyGameObject {
    static final float width = Constants.getConstant("bullet.Width");
    static final float height = Constants.getConstant("bullet.Height");
    Board board;
    ModelPlayer color;
    boolean isActive = true;
    public Bullet(BulletCreatingParams BCP, Board board){
        super(BCP,board.getWorld());
        body.setUserData(this);
        color = BCP.getColor();
        this.board = board;
    }
    public void update(float time){
        Vector2 vel = body.getLinearVelocity();
        vel.x = ModelSettings.getBulletSpeed() * MathUtils.cos(body.getAngle());
        vel.y = ModelSettings.getBulletSpeed() * MathUtils.sin(body.getAngle());
        body.setLinearVelocity(vel);
    }
    public void takeDamage(BodyGameObject killer){
        if(!isActive) return;
        if(killer instanceof Tank && ((Tank) killer).getColor() == color) return;
        if(killer instanceof Spawn && ((Spawn) killer).getColor() == color) return;
        if(killer instanceof Bullet && ((Bullet) killer).getColor() == color) return;
        if(killer instanceof Plate) return;
        if(killer instanceof ExplosionRectangle) return;
        if(killer instanceof HedgehogsObstacle) return;
        if(killer instanceof ExplosionCircle && !(killer instanceof DynamiteExplosion)) return;
        if(killer instanceof DynamiteExplosion && ((DynamiteExplosion) killer).getStateTime()>((DynamiteExplosion) killer).getLiveTime()/2) return;
        isActive = false;
        float angle = getRotation() * MathUtils.degreesToRadians;
        float x = getX() + MathUtils.cos(angle) * height * 0.5f;
        float y = getY() + MathUtils.sin(angle) * width * 0.5f;
        board.addObjectToCreate(new BulletExplosionCreatingParams().setX(x).setY(y).setRotation(getRotation()).setColor(getColor()));
    }
    public ModelPlayer getColor(){
        return color;
    }
    public boolean getIsActive() { return isActive; }
    @Override
    public BulletDescription getDescription()
    {
        return (BulletDescription) (new BulletDescription())
                .setX(getX())
                .setY(getY())
                .setWidth(getWidth())
                .setHeight(getHeight())
                .setRotation(getRotation());
    }
}