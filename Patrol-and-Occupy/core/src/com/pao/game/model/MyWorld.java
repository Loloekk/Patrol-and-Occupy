package com.pao.game.model;

import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.GameObject.Bodies.BodyCreator;
import com.pao.game.model.MultiContactListener.MultiContactListener;

import static com.pao.game.constants.Box2dConstants.GRAVITY;

public class MyWorld {
    public static World createMyWorld()
    {
        World world = new World(GRAVITY, true);
        MultiContactListener multiContactListener = new MultiContactListener();
        world.setContactListener(multiContactListener);
        BodyCreator.setEdges(0,0, ModelSettings.getWidth(), ModelSettings.getHeight(), world);
        return world;
    }
}
