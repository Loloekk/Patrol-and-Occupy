package com.pao.game.view.GameScreen.Drawing;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.communication.Descriptions.ConcreteDescription.*;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.GameScreen.Drawing.ObjectDrawing.*;
import com.pao.game.view.RegionPainter;
import com.pao.game.view.Textures;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class PrepareDrawingObjects {
    RegionPainter painterGame;
    RegionPainter painterTop;

    public PrepareDrawingObjects(RegionPainter painterGame, RegionPainter painterTop){
        this.painterGame = painterGame;
        this.painterTop = painterTop;
    }
    public void prepare(List<ObjectDescription> objects, List<ObjectDrawing> objectsDrawings)
    {
        for(ObjectDescription desc : objects)
        {
            if(desc instanceof BulletExplosionDescription) {
                objectsDrawings.add(new BulletExplosionDrawing(painterGame, (BulletExplosionDescription) desc));
            }
            else if(desc instanceof BulletShootDescription) {
                objectsDrawings.add(new BulletShootDrawing(painterGame, (BulletShootDescription) desc));
            }
            else if(desc instanceof DynamiteExplosionDescription) {
                objectsDrawings.add(new DynamiteExplosionDrawing(painterGame, (DynamiteExplosionDescription) desc));
            }
            else if(desc instanceof BreakableObstacleDescription) {
                objectsDrawings.add(new BreakableObstacleDrawing(painterGame, (BreakableObstacleDescription) desc));
            }
            else if(desc instanceof UnbreakableObstacleDescription) {
                objectsDrawings.add(new UnbreakableObstacleDrawing(painterGame, (UnbreakableObstacleDescription) desc));
            }
            else if(desc instanceof BulletDescription) {
                objectsDrawings.add(new BulletDrawing(painterGame, (BulletDescription) desc));
            }
            else if(desc instanceof DynamiteDescription) {
                objectsDrawings.add(new DynamiteDrawing(painterGame, (DynamiteDescription) desc));
            }
            else if(desc instanceof PlateDescription) {
                objectsDrawings.add(new PlateDrawing(painterGame, (PlateDescription) desc));
            }
            else if(desc instanceof SpawnDescription) {
                objectsDrawings.add(new SpawnDrawing(painterGame, (SpawnDescription) desc));
            }
            else if(desc instanceof TankDescription)
                objectsDrawings.add(new TankDrawing(painterGame,painterTop,(TankDescription) desc));
            else continue;
        }
        Collections.sort(objectsDrawings, new Comparator<ObjectDrawing>() {
            @Override
            public int compare(ObjectDrawing l1, ObjectDrawing l2) {
                return l1.getPriority().compareTo(l2.getPriority());
            }
        });
    }
}
