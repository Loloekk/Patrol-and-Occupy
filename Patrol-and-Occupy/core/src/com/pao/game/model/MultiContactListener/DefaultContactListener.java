package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.*;

public class DefaultContactListener implements ContactListener {
    Board board;
    public DefaultContactListener(Board board) { this.board = board; }
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof BodyGameObject && userDataB instanceof BodyGameObject) {
            ((BodyGameObject) userDataA).takeDamage((BodyGameObject) userDataB);
            ((BodyGameObject) userDataB).takeDamage((BodyGameObject) userDataA);
        }
        else if(userDataA instanceof BodyGameObject){
            ((BodyGameObject) userDataA).takeDamage(null);
        }
        else if(userDataB instanceof BodyGameObject){
            ((BodyGameObject) userDataB).takeDamage(null);
        }}
    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
