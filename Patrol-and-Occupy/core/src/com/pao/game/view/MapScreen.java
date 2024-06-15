package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.constants.ModelConstants;
import com.pao.game.constants.ViewConstants;
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.*;

public class MapScreen implements Screen {
    final float BUTTON_WIDTH = ViewConstants.getFloatConstant("MapScreen.Button.Width");
    final float BUTTON_HEIGHT = ViewConstants.getFloatConstant("MapScreen.Button.Height");
    final float BACK_BUTTON_Y = ViewConstants.getFloatConstant("MapScreen.Back.Button.Y");
    final float TABLE_WIDTH=ViewConstants.getFloatConstant("MapScreen.Table.Width");
    final float TABLE_HEIGHT=ViewConstants.getFloatConstant("MapScreen.Table.Height");
    final float TABLE_PAD=ViewConstants.getFloatConstant("MapScreen.Table.Pad");
    final float SCROLL_PLANE_WIDTH =ViewConstants.getFloatConstant("MapScreen.Scroll.Plane.Width");
    final float SCROLL_PLANE_HEIGHT =ViewConstants.getFloatConstant("MapScreen.Scroll.Plane.Height");
    final float SCROLL_PLANE_OFF =ViewConstants.getFloatConstant("MapScreen.Scroll.Plane.Off");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("MapScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("MapScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("MapScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("MapScreen.BackGround.A");
    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Vector3 touchPoint;
    RegionPainter painter;
    Stage stage;
    Skin skin;
    Table table;
    ScrollPane scrollPane;
    Rectangle backButton;
    public MapScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);

        backButton = new Rectangle(Drop.WIDTH/2 - BUTTON_WIDTH/2, BACK_BUTTON_Y - BUTTON_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A));

        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal(ViewConstants.getConstant("MapScreen.Skin")));
        int numberOfMaps = (int) ModelConstants.getConstant("Number.Of.Maps");
        Texture[] txs = new Texture[numberOfMaps];
        for(int i = 0 ;i < numberOfMaps ;i ++){
            txs[i]=Textures.getTexture("setup."+i);
        }
        table = new Table();
        for(int i = 0; i < txs.length; i++) {
            int index = i;
            Image image = new Image(txs[i]);
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    EditSettings.setOption(map, index);
                    game.setScreen(new GameScreen(game));
                }
            });

            table.add(image).size(TABLE_WIDTH, TABLE_HEIGHT).pad(TABLE_PAD);
        }

        scrollPane = new ScrollPane(table);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setSize(SCROLL_PLANE_WIDTH, SCROLL_PLANE_HEIGHT);
        scrollPane.setPosition(Drop.WIDTH/2-scrollPane.getWidth()/2, Drop.HEIGHT/2- scrollPane.getHeight()/2 + SCROLL_PLANE_OFF);

        scrollPane.setFlickScroll(true);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setOverscroll(true, false);
        scrollPane.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                stage.setScrollFocus(scrollPane);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                stage.setScrollFocus(null);
            }
        });

        stage.addActor(scrollPane);
    }
    @Override
    public void show() { Gdx.input.setInputProcessor(stage);}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground();
        camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        painter.drawAlternatingTexture(Textures.getTexture("back.Button.Active"), Textures.getTexture("back.Button.Inactive"), backButton, touchPoint);
        if(backButton.contains(touchPoint.x, touchPoint.y) && Gdx.input.justTouched()) game.setScreen(new PlayerNumberScreen(game));

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { viewport.update(width, height); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() { Gdx.input.setInputProcessor(null); }

    @Override
    public void dispose() {

    }
}
