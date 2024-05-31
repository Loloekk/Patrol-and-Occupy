package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Bullet;
import com.pao.game.model.GameObject.Dynamite;
import com.pao.game.model.GameObject.Tank;

public class BulletContactListener implements ContactListener {
    Board board;
    public BulletContactListener(Board board) { this.board = board; }
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
        else if (userDataA instanceof Bullet && userDataB instanceof Dynamite) {
            board.destroyDynamite((Dynamite) userDataB, ((Bullet) userDataA).getColor());
        }
        else if (userDataA instanceof Dynamite && userDataB instanceof Bullet) {
            board.destroyDynamite((Dynamite) userDataA, ((Bullet) userDataB).getColor());
        }
        else if (userDataA instanceof Bullet) {
            board.destroyBullet((Bullet) userDataA);
        }
        else if (userDataB instanceof Bullet) {
            board.destroyBullet((Bullet) userDataB);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
    private void handleBulletTankContact(Bullet bullet, Tank tank, Contact contact) {
        if (bullet.getColor() == tank.getColor()) {
            contact.setEnabled(false);
        }
        else {
            board.destroyBullet(bullet);
            board.destroyTank(tank, bullet.getColor());
        }
    }
}
