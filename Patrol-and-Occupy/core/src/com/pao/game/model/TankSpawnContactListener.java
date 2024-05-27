package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.*;

public class TankSpawnContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }
    @Override
    public void endContact(Contact contact) {

    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Tank && userDataB instanceof Spawn) {
            handleTankBaseContact((Tank) userDataA, (Spawn) userDataB, contact);
        }
        else if (userDataA instanceof Spawn && userDataB instanceof Tank) {
            handleTankBaseContact((Tank) userDataB, (Spawn) userDataA, contact);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    private void handleTankBaseContact(Tank tank, Spawn spawn, Contact contact) {
        if (tank.getColor() == spawn.getColor()) {
            contact.setEnabled(false);
        }
        else {
            contact.setEnabled(true);
        }
    }
}
