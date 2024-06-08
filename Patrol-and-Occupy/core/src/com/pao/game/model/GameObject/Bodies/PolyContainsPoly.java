package com.pao.game.model.GameObject.Bodies;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PolyContainsPoly {
    private PolyContainsPoly()
    {
        throw new UnsupportedOperationException("Cannot instantiate PolyContainsPoly class, Nunu");
    }
    public static boolean isPointInPolygon(Polygon polygon, Vector2 point) {
        return polygon.contains(point.x, point.y);
    }
    public static Polygon createPolygonFromShape(PolygonShape shape, Body body) {
        int vertexCount = shape.getVertexCount();
        float[] vertices = new float[vertexCount * 2];
        for (int i = 0; i < vertexCount; i++) {
            Vector2 vertex = new Vector2();
            shape.getVertex(i, vertex);
            vertex = body.getWorldPoint(vertex);
            vertices[i * 2] = vertex.x;
            vertices[i * 2 + 1] = vertex.y;
        }
        return new Polygon(vertices);
    }
    public static boolean isPolyInPoly(Body tank, Body spawnArea) {
        PolygonShape tankShape = (PolygonShape) tank.getFixtureList().get(0).getShape();
        PolygonShape spawnShape = (PolygonShape) spawnArea.getFixtureList().get(0).getShape();
        Polygon spawnPolygon = createPolygonFromShape(spawnShape, spawnArea);

        int vertexCount = tankShape.getVertexCount();
        for (int i = 0; i < vertexCount; i++) {
            Vector2 vertex = new Vector2();
            tankShape.getVertex(i, vertex);
            vertex = tank.getWorldPoint(vertex);
            if (!isPointInPolygon(spawnPolygon, vertex)) {
                return false;
            }
        }
        return true;
    }
}
