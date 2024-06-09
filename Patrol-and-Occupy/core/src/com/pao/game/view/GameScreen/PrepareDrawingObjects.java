package com.pao.game.view.GameScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pao.game.communication.Descriptions.ConcreteDescription.*;
import com.pao.game.communication.Descriptions.ObjectDescription;
import com.pao.game.view.Animations;
import com.pao.game.view.Textures;
import com.pao.game.viewmodel.ViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class PrepareDrawingObjects {

    public PrepareDrawingObjects(){}
    public void prepare(List<ObjectDescription> objects,List<MagazineView> magazines, List<DrawingObject> drawingObjects)
    {
        for(ObjectDescription desc : objects)
        {
            DrawingObject draw = new DrawingObject();
            draw.setDescription(desc);
            TextureRegion texture;
            if(desc instanceof BulletExplosionDescription) {
                texture = Animations.getBulletExplosionAnimation().getKeyFrame(((BulletExplosionDescription)desc).getStateTime());
                draw.setPriority(3000);
            }
            else if(desc instanceof BulletShootDescription) {
                texture = Animations.getBulletShootAnimation().getKeyFrame(((BulletShootDescription)desc).getStateTime());
                desc.setWidth(desc.getWidth()*4).setHeight(desc.getHeight()*4);
                draw.setPriority(2000);
            }
            else if(desc instanceof DynamiteExplosionDescription) {
                texture = Animations.getDynamiteExplosionAnimation().getKeyFrame(((DynamiteExplosionDescription)desc).getStateTime());
                desc.setWidth(desc.getWidth()*2f).setHeight(desc.getHeight()*2f);
                draw.setPriority(1000);
            }
            else if(desc instanceof BreakableObstacleDescription) {
                texture = new TextureRegion(Textures.getBreakableObstacleTexture());
                desc.setWidth(desc.getWidth()+2).setHeight(desc.getHeight()+2);
                draw.setPriority(100);
            }
            else if(desc instanceof UnbreakableObstacleDescription) {
                texture = new TextureRegion(Textures.getUnbreakableObstacleTexture());
                desc.setWidth(desc.getWidth()+2).setHeight(desc.getHeight()+2);
                draw.setPriority(100);
            }
            else if(desc instanceof BulletDescription) {
                texture = new TextureRegion(Textures.getBulletTexture());
                draw.setPriority(500);
            }
            else if(desc instanceof DynamiteDescription) {
                texture = new TextureRegion(Textures.getDynamiteTexture());
                draw.setPriority(300);
            }
            else if(desc instanceof PlateDescription) {
                texture = new TextureRegion(Textures.getPlateTexture(((PlateDescription)desc).getColor()));
                draw.setPriority(200);
            }
            else if(desc instanceof SpawnDescription) {
                texture = new TextureRegion(Textures.getSpawnTexture(((SpawnDescription)desc).getColor()));
                draw.setPriority(400);
            }
            else if(desc instanceof TankDescription) {
                texture = new TextureRegion(Textures.getTankTexture(((TankDescription) desc).getIsAlive() ? ((TankDescription) desc).getColor() : null));
                magazines.add(new MagazineView((TankDescription) desc));
                draw.setPriority(600);
            }
            else continue;
            draw.setTexture(new TextureRegion(texture));
            drawingObjects.add(draw);
        }
        Collections.sort(drawingObjects, new Comparator<DrawingObject>() {
            @Override
            public int compare(DrawingObject l1, DrawingObject l2) {
                return l1.getPriority().compareTo(l2.getPriority());
            }
        });
    }
}
