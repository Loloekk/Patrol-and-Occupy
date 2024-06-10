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
import com.pao.game.view.GameScreen.GameScreen;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.*;

public class MapScreen implements Screen {
    static final float BUTTON_WIDTH = 300 ;
    static final float BUTTON_HEIGHT = 200 ;
    static final float BACK_BUTTON_Y = 250 ;
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
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));

        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Texture[] txs = new Texture[8];
        txs[0] = Textures.getTexture("setup.0");
        txs[1] = Textures.getTexture("setup.1");
        txs[2] = Textures.getTexture("setup.2");
        txs[3] = Textures.getTexture("setup.3");
        txs[4] = Textures.getTexture("setup.4");
        txs[5] = Textures.getTexture("setup.5");
        txs[6] = Textures.getTexture("setup.6");
        txs[7] = Textures.getTexture("setup.7");

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

            table.add(image).pad(10);
        }

        scrollPane = new ScrollPane(table);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setSize(800, 600);
        scrollPane.setPosition(Drop.WIDTH/2-scrollPane.getWidth()/2, Drop.HEIGHT/2- scrollPane.getHeight()/2 + 100);

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
        ScreenUtils.clear(0.4f,0.4f,0.9f,1);
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
