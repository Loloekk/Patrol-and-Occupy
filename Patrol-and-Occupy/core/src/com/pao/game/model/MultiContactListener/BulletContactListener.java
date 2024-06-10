package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.GameObject.Explosions.Explosion.ExplosionRectangle;
import com.pao.game.model.GameObject.Obstacles.HedgehogsObstacl.HedgehogsObstacle;
import com.pao.game.model.GameObject.Others.Bullet.Bullet;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class BulletContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {}
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Bullet && userDataB instanceof Tank) {
            handleBulletTankContact((Bullet) userDataA, (Tank) userDataB, contact);
        }
        else if (userDataA instanceof Tank && userDataB instanceof Bullet) {
            handleBulletTankContact((Bullet) userDataB, (Tank) userDataA, contact);
        }
        if (userDataA instanceof Bullet && userDataB instanceof Spawn) {
            handleBulletSpawnContact((Bullet) userDataA, (Spawn) userDataB, contact);
        }
        else if (userDataA instanceof Spawn && userDataB instanceof Bullet) {
            handleBulletSpawnContact((Bullet) userDataB, (Spawn) userDataA, contact);
        }
        if (userDataA instanceof Bullet && userDataB instanceof Bullet) {
            handleBulletBulletContact((Bullet)userDataA,(Bullet)userDataB,contact);
        }
        if (userDataA instanceof Bullet && userDataB instanceof ExplosionRectangle) {
            contact.setEnabled(false);
        }
        else if (userDataA instanceof ExplosionRectangle && userDataB instanceof Bullet){
            contact.setEnabled(false);
        }
        if (userDataA instanceof Bullet && userDataB instanceof HedgehogsObstacle) {
            contact.setEnabled(false);
        }
        else if (userDataA instanceof HedgehogsObstacle && userDataB instanceof Bullet){
            contact.setEnabled(false);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
    private void handleBulletTankContact(Bullet bullet, Tank tank, Contact contact) {
        if (bullet.getColor() == tank.getColor()) {
            contact.setEnabled(false);
        }
    }
    private void handleBulletSpawnContact(Bullet bullet, Spawn spawn, Contact contact) {
        if (bullet.getColor() == spawn.getColor()) {
            contact.setEnabled(false);
        }
    }
    private void handleBulletBulletContact(Bullet bullet1, Bullet bullet2, Contact contact) {
        if (bullet1.getColor() == bullet2.getColor()) {
            contact.setEnabled(false);
        }
    }
}
