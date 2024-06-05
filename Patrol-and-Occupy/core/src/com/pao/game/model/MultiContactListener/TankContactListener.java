package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.BreakableObstacle;
import com.pao.game.model.GameObject.Plate;
import com.pao.game.model.GameObject.Spawn;
import com.pao.game.model.GameObject.Tank;


public class TankContactListener implements ContactListener {
    Board board;
    public TankContactListener(Board board) {
        this.board = board;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Tank && userDataB instanceof Plate) {
            board.changePlateOwner((Plate) userDataB, (Tank) userDataA);
        }
        else if(userDataA instanceof Plate && userDataB instanceof Tank) {
            board.changePlateOwner((Plate) userDataA, (Tank) userDataB);
        }
    }
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Tank && userDataB instanceof Spawn) {
            handleTankSpawnContact((Tank) userDataA, (Spawn) userDataB, contact);
        }
        else if (userDataA instanceof Spawn && userDataB instanceof Tank) {
            handleTankSpawnContact((Tank) userDataB, (Spawn) userDataA, contact);
        }
        else if (userDataA instanceof Tank && userDataB instanceof BreakableObstacle) {
            ((BreakableObstacle) userDataB).takeDamage();
        }
        else if(userDataA instanceof BreakableObstacle && userDataB instanceof Tank) {
            ((BreakableObstacle) userDataA).takeDamage();
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}
    private void handleTankSpawnContact(Tank tank, Spawn spawn, Contact contact) {
        if (tank.getColor() == spawn.getColor()) {
            contact.setEnabled(false);
        }
        else {
            contact.setEnabled(true);
        }
    }
}
