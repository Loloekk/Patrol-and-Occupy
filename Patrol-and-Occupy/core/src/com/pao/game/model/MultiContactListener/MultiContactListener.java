package com.pao.game.model.MultiContactListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pao.game.model.Boards.Board;
import com.pao.game.model.GameObject.Tank;
import com.sun.org.apache.xpath.internal.operations.Mult;

import java.util.ArrayList;
import java.util.List;

public class MultiContactListener implements ContactListener {
    List<ContactListener> listeners = new ArrayList<>();
    Board board;
    public MultiContactListener()
    {
        listeners.add(new DefaultContactListener(board));
        listeners.add(new TankContactListener(board));
        listeners.add(new BulletContactListener(board));
    }
    @Override
    public void beginContact(Contact contact) {
        for (ContactListener listener : listeners) {
            listener.beginContact(contact);
        }
    }
    @Override
    public void endContact(Contact contact) {
        for (ContactListener listener : listeners) {
            listener.endContact(contact);
        }
    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        for (ContactListener listener : listeners) {
            listener.preSolve(contact, oldManifold);
        }
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        for (ContactListener listener : listeners) {
            listener.postSolve(contact, impulse);
        }
    }
}
