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
    Textures text;
    Animations animations;
    public PrepareDrawingObjects(Textures text, Animations animations){
        this.text = text;
        this.animations = animations;
    }
    public void prepare(List<ObjectDescription> objects,List<MagazineView> magazines, List<DrawingObject> drawingObjects)
    {
        for(ObjectDescription desc : objects)
        {
            DrawingObject draw = new DrawingObject();
            draw.setDescription(desc);
            TextureRegion texture;
            if(desc instanceof BulletExplosionDescription) {
                texture = animations.getBulletExplosionAnimation().getKeyFrame(((BulletExplosionDescription)desc).getStateTime());
                draw.setPriority(3000);
            }
            else if(desc instanceof BulletShootDescription) {
                texture = animations.getBulletShootAnimation().getKeyFrame(((BulletShootDescription)desc).getStateTime());
                draw.setPriority(2000);
            }
            else if(desc instanceof DynamiteExplosionDescription) {
                texture = animations.getDynamiteExplosionAnimation().getKeyFrame(((DynamiteExplosionDescription)desc).getStateTime());
                desc.setWidth(desc.getWidth()*2f).setHeight(desc.getHeight()*2f);
                draw.setPriority(1000);
            }
            else if(desc instanceof BreakableObstacleDescription) {
                texture = new TextureRegion(text.getBreakableObstacleTexture());
                desc.setWidth(desc.getWidth()+2).setHeight(desc.getHeight()+2);
                draw.setPriority(100);
            }
            else if(desc instanceof UnbreakableObstacleDescription) {
                texture = new TextureRegion(text.getUnbreakableObstacleTexture());
                desc.setWidth(desc.getWidth()+2).setHeight(desc.getHeight()+2);
                draw.setPriority(100);
            }
            else if(desc instanceof BulletDescription) {
                texture = new TextureRegion(text.getBulletTexture());
                draw.setPriority(500);
            }
            else if(desc instanceof DynamiteDescription) {
                texture = new TextureRegion(text.getDynamiteTexture());
                draw.setPriority(300);
            }
            else if(desc instanceof PlateDescription) {
                texture = new TextureRegion(text.getPlateTexture(((PlateDescription)desc).getColor()));
                draw.setPriority(200);
            }
            else if(desc instanceof SpawnDescription) {
                texture = new TextureRegion(text.getSpawnTexture(((SpawnDescription)desc).getColor()));
                draw.setPriority(400);
            }
            else if(desc instanceof TankDescription) {
                texture = new TextureRegion(text.getTankTexture(((TankDescription) desc).getIsAlive() ? ((TankDescription) desc).getColor() : null));
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
