package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.GameObject.Others.Spawn.Spawn;
import com.pao.game.model.GameObject.Others.Tank.Tank;

public class TankContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
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
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}
    private void handleTankSpawnContact(Tank tank, Spawn spawn, Contact contact) {
        if (tank.getColor() == spawn.getColor()) {
            contact.setEnabled(false);
        }
    }
}
