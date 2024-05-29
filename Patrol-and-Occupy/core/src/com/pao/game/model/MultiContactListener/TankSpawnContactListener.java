package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.Spawn;
import com.pao.game.model.Tank;

public class TankSpawnContactListener implements ContactListener {
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

        if (userDataA instanceof Tank && userDataB instanceof Spawn) {
            handleTankSpawnContact((Tank) userDataA, (Spawn) userDataB, contact);
        }
        else if (userDataA instanceof Spawn && userDataB instanceof Tank) {
            handleTankSpawnContact((Tank) userDataB, (Spawn) userDataA, contact);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
    private void handleTankSpawnContact(Tank tank, Spawn spawn, Contact contact) {
        if (tank.getColor() == spawn.getColor()) {
            contact.setEnabled(false);
        }
        else {
            contact.setEnabled(true);
        }
    }
}
