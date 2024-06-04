package com.pao.game.model.GameObject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;

import static com.pao.game.model.Constants.PPM;

public class BodyCreator {
    BodyCreator(){}
    public static Body createBodyRectangle(float x, float y, float width, float height, float degree,
                                         final BodyDef.BodyType bodyType, final World world, float density, boolean isSensor){//, float friction) {
        final BodyDef bdef = new BodyDef();
        bdef.position.set(x / PPM, y / PPM);
        bdef.type = bodyType;
        final Body body = world.createBody(bdef);

        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2 /PPM, height/2 /PPM);

        final FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = density;
        fdef.isSensor = isSensor;
        //fdef.friction = friction;

        body.createFixture(fdef);

        body.setTransform(x/PPM, y/PPM, degree * MathUtils.degreesToRadians);
        shape.dispose();
        return body;
    }
    public static void createEdgeBody(float x1, float y1, float x2, float y2, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        EdgeShape edge = new EdgeShape();
        edge.set(x1 / PPM, y1 / PPM, x2 / PPM, y2 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = edge;
        body.createFixture(fixtureDef);
        edge.dispose();
    }

    public static void setEdges(float x1, float y1, float x2, float y2, World world) {
        createEdgeBody(x1, y1, x2, y1, world);        // Bottom edge
        createEdgeBody(x2, y1, x2, y2, world);        // Right edge
        createEdgeBody(x1, y2, x2, y2, world);        // Upper edge
        createEdgeBody(x1, y1, x1, y2, world);        // Left edge
    }
}
