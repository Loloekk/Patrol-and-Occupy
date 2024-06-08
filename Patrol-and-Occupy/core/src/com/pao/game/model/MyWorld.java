package com.pao.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.pao.game.model.GameObject.Bodies.BodyCreator;
import com.pao.game.model.MultiContactListener.MultiContactListener;

import static com.pao.game.model.Constants.GRAVITY;

public class MyWorld {
    public static World createMyWorld()
    {
        World world = new World(GRAVITY, true);
        MultiContactListener multiContactListener = new MultiContactListener();
        world.setContactListener(multiContactListener);
        BodyCreator.setEdges(0,0, ModelSettings.getWidth(), ModelSettings.getHeight(), world);
        return world;
    }
    public static boolean isBodyInWorld(Body body) {
        Vector2 position = body.getPosition();

        if (position.x < 0 || position.x > ModelSettings.getWidth() || position.y < 0 || position.y > ModelSettings.getHeight()) {
            return true;
        } else {
            return false;
        }
    }
}
