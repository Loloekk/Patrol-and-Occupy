package com.pao.game.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.pao.game.model.Constants.PPM;

public class PolygonBody {
    public static Polygon getPolygonFromBody(Body body) {
        PolygonShape shape = (PolygonShape) body.getFixtureList().first().getShape();

        int vertexCount = shape.getVertexCount();
        float[] vertices = new float[vertexCount * 2];

        Vector2 vertex = new Vector2();
        for (int i = 0; i < vertexCount; i++) {
            shape.getVertex(i, vertex);
            vertices[i * 2] = vertex.x * PPM;
            vertices[i * 2 + 1] = vertex.y * PPM;
        }

        Polygon polygon = new Polygon(vertices);

        Vector2 bodyPosition = body.getPosition();
        float bodyAngle = body.getAngle() * MathUtils.radiansToDegrees;
        polygon.setPosition(bodyPosition.x * PPM, bodyPosition.y * PPM);
        polygon.setRotation(bodyAngle);

        return polygon;
    }
}
