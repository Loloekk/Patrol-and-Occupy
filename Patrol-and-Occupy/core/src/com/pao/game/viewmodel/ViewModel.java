package com.pao.game.viewmodel;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.communication.*;
import com.pao.game.model.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.Boards.SimpleBoard;
import com.pao.game.model.GameObject.*;
import com.pao.game.model.MultiContactListener.BulletContactListener;
import com.pao.game.model.MultiContactListener.MultiContactListener;
import com.pao.game.model.MultiContactListener.TankContactListener;

import java.util.ArrayList;
import java.util.List;

import static com.pao.game.model.Constants.*;

public class ViewModel{
    Board board;
    World world;
    MultiContactListener multiContactListener;
    GlobalStatistics globalStatistics;
    public ViewModel()
    {
        board = new SimpleBoard();
        globalStatistics = new GlobalStatistics(this);
    }

    public void setMove(ModelPlayer color, Move move, boolean state)
    {
        board.setMove(color,move,state);
    }
    public List<TankParams> getTanks() {
        List<TankParams> tanksParamsList = new ArrayList<>();
        for(BodyGameObject tank : board.getBodyObjects()) if(tank instanceof Tank){
            tanksParamsList.add(new TankParams(((Tank) tank).getColor(),
                    tank.getWidth(),tank.getHeight(),tank.getX(),tank.getY(),
                    tank.getRotation(), ((Tank) tank).getMagazine().getQuantity(), ((Tank) tank).getStatistics().getNumberOfPlates(), ((Tank) tank).getIsAlive(),((Tank) tank).getMagazine().hasDynamite()));
        }
        return tanksParamsList;
    }
    public Tank getTank(ModelPlayer color){
        return board.getTank(color);
    }
    public List<ColoredParams> getBullets() {
        List<ColoredParams> bulletsParamsList = new ArrayList<>();
        for(BodyGameObject bullet : board.getBodyObjects()) if(bullet instanceof Bullet) {
            bulletsParamsList.add(new ColoredParams(((Bullet) bullet).getColor(), bullet.getWidth(), bullet.getHeight(), bullet.getX(), bullet.getY(), bullet.getRotation()));
        }
        return bulletsParamsList;
    }
    public List<Params> getObstacles() {
        List<Params> obstaclesParamsList = new ArrayList<>();
        for(BodyGameObject unbreakableObstacle : board.getBodyObjects()) if(unbreakableObstacle instanceof UnbreakableObstacle){
            obstaclesParamsList.add(new Params(unbreakableObstacle.getWidth(), unbreakableObstacle.getHeight(), unbreakableObstacle.getX(), unbreakableObstacle.getY(), unbreakableObstacle.getRotation()));
        }
        return obstaclesParamsList;
    }
    public List<Params> getBreakableObstacles() {
        List<Params> breakableObstaclesParamsList = new ArrayList<>();
        for(BodyGameObject breakableObstacle : board.getBodyObjects()) if(breakableObstacle instanceof BreakableObstacle) {
            breakableObstaclesParamsList.add(new Params(breakableObstacle.getWidth(), breakableObstacle.getHeight(), breakableObstacle.getX(), breakableObstacle.getY(), breakableObstacle.getRotation()));
        }
        return breakableObstaclesParamsList;
    }

    public List<ColoredParams> getPlates() {
        List<ColoredParams> platesParamsList = new ArrayList<>();
        for(BodyGameObject plate : board.getBodyObjects()) if(plate instanceof Plate){
            platesParamsList.add(new ColoredParams(((Plate) plate).getColor(), plate.getWidth(), plate.getHeight(), plate.getX(), plate.getY(), plate.getRotation()));
        }
        return platesParamsList;
    }
    public List<Params> getDynamites() {
        List<Params> dynamitesParamsList = new ArrayList<>();
        for(BodyGameObject dynamite : board.getBodyObjects()) if(dynamite instanceof Dynamite){
            dynamitesParamsList.add(new Params(dynamite.getWidth(), dynamite.getHeight(), dynamite.getX(), dynamite.getY(), dynamite.getRotation()));
        }
        return dynamitesParamsList;
    }
    public List<ColoredParams> getSpawns() {
        List<ColoredParams> spawnsParamsList = new ArrayList<>();
        for(BodyGameObject spawn : board.getBodyObjects()) if(spawn instanceof Spawn) {
            spawnsParamsList.add(new ColoredParams(((Spawn) spawn).getColor(), spawn.getWidth(), spawn.getHeight(), spawn.getX(), spawn.getY(), spawn.getRotation()));
        }
        return spawnsParamsList;
    }
    public List<ExplosionParams> getDynamiteExplosions() {
        List<ExplosionParams> dynamiteExplosionsParamsList = new ArrayList<>();
        for(Explosion explosion : board.getDynamiteExplosionList()) {
            dynamiteExplosionsParamsList.add(new ExplosionParams(explosion.getWidth(), explosion.getHeight(), explosion.getX(), explosion.getY(), explosion.getRotation(), explosion.getStateTime()));
        }
        return dynamiteExplosionsParamsList;
    }
    public List<ExplosionParams> getBulletShoots() {
        List<ExplosionParams> bulletShootsParamsList = new ArrayList<>();
        for(BulletShoot bulletShoot : board.getBulletShootList()) {
            bulletShootsParamsList.add(new ExplosionParams(bulletShoot.getWidth(), bulletShoot.getHeight(), bulletShoot.getX(), bulletShoot.getY(), bulletShoot.getRotation(), bulletShoot.getStateTime()));
        }
        return bulletShootsParamsList;
    }
    public List<ExplosionParams> getBulletExplosions() {
        List<ExplosionParams> bulletExplosionsParamsList = new ArrayList<>();
        for(BulletExplosion bulletExplosion : board.getBulletExplosionList()) {
            bulletExplosionsParamsList.add(new ExplosionParams(bulletExplosion.getWidth(), bulletExplosion.getHeight(), bulletExplosion.getX(), bulletExplosion.getY(), bulletExplosion.getRotation(), bulletExplosion.getStateTime()));
        }
        return bulletExplosionsParamsList;
    }
    public float getRemainingTime()
    {
        return board.getRemainingTime();
    }
    public GlobalStatistics getStatistics() {
        return globalStatistics;
    }
    public void update(float time) {
        board.update(time);
    }
}