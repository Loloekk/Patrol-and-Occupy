package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.GameObject.Explosions.Explosion.Explosion;


public class ExplosionContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {}
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Explosion || userDataB instanceof Explosion) {
            contact.setEnabled(false);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}
}
