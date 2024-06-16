package com.pao.game.model.GameObject.Bodies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsCircle;
import com.pao.game.model.GameObject.CreatingParams.CreatingParamsRectangle;

import static com.pao.game.constants.Box2dConstants.PPM;

public class BodyCreator {
    private BodyCreator()
    {
        throw new UnsupportedOperationException("Cannot instantiate BodyCreator class");
    }
    public static Body createBodyRectangle(CreatingParamsRectangle CPR, World world) {
        final BodyDef bdef = new BodyDef();
        bdef.position.set(CPR.getX() / PPM, CPR.getY() / PPM);
        bdef.type = CPR.getBodyType();
        final Body body = world.createBody(bdef);

        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(CPR.getRealWidth()/2 /PPM, CPR.getRealHeight()/2 /PPM);

        final FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = CPR.getDensity();
        fdef.isSensor = CPR.getIsSensor();

        body.createFixture(fdef);

        body.setTransform(CPR.getX()/PPM, CPR.getY()/PPM, CPR.getRotation() * MathUtils.degreesToRadians);
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

    public static Body createBodyCircle(CreatingParamsCircle CPC, World world) {
        final BodyDef bdef = new BodyDef();
        bdef.position.set(CPC.getX() / PPM, CPC.getY() / PPM);
        bdef.type = CPC.getBodyType();
        final Body body = world.createBody(bdef);
        final CircleShape shape = new CircleShape();
        shape.setRadius(CPC.getRadius()/PPM);

        final FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = CPC.getDensity();
        fdef.isSensor = CPC.getIsSensor();

        body.createFixture(fdef);

        body.setTransform(CPC.getX()/PPM, CPC.getY()/PPM, CPC.getRotation() * MathUtils.degreesToRadians);
        shape.dispose();
        return body;
    }
}
