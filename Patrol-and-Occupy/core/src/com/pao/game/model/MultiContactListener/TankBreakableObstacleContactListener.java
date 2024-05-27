package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.BreakableObstacle;
import com.pao.game.model.Tank;


public class TankBreakableObstacleContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }
    @Override
    public void endContact(Contact contact) {

    }
    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if ((userDataA instanceof Tank && userDataB instanceof BreakableObstacle) || (userDataA instanceof BreakableObstacle && userDataB instanceof Tank)) {
            contact.setEnabled(false);
        }else{
            contact.setEnabled(true);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
