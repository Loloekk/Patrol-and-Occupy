package com.pao.game.view.GameScreen.Drawing.ObjectDrawing;

import com.pao.game.view.RegionPainter;

public class SpacePainting implements  ObjectDrawing{
    RegionPainter painter;
    final float priority;
    public SpacePainting(float priority, RegionPainter painter)
    {
        this.priority=priority;
        this.painter=painter;
    }
    @Override
    public Float getPriority() {
        return priority;
    }
    @Override
    public void draw() {
        painter.fillBackground();
    }
}
