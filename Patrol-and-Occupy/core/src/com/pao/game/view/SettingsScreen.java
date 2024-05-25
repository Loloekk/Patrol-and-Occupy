package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.model.Options;
import com.pao.game.viewmodel.ViewModel;

import static com.pao.game.model.Constants.PPM;
import static com.pao.game.model.Options.*;

public class SettingsScreen implements Screen {
    static final float BUTTON_WIDTH = 300 / PPM;
    static final float BUTTON_HEIGHT = 200 / PPM;
    static final float EXIT_BUTTON_Y = 700 / PPM;
    Drop game;
    Screen previousScreen;
    OrthographicCamera camera;
    ViewModel VM;
    Viewport viewport;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Rectangle exitButton;
    Vector3 touchPoint;
    RegionPainter painter;
    Stage stage;
    Skin skin;
    float sliderWidth = 200f;
    float sliderHeight = 20f;

    public SettingsScreen(Drop game, ViewModel VM, Screen previousScreen) {
        this.game = game;
        this.VM = VM;
        this.previousScreen = previousScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Slider tankSpeedSlider = makeSlider(1, 20, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 600f, tankSpeed);
        stage.addActor(tankSpeedSlider);
        Slider bulletSpeedSlider = makeSlider(400, 1000, 50, false, Drop.WIDTH/2 + sliderWidth/2, 600f, bulletSpeed);
        stage.addActor(bulletSpeedSlider);
        Slider magazineCapacitySlider = makeSlider(1, 10, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 500f, magazineCapacity);
        stage.addActor(magazineCapacitySlider);
        Slider shootCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 + sliderWidth/2, 500f, shootCooldown);
        stage.addActor(shootCooldownSlider);
        Slider receiveCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 - sliderWidth*3/2, 400f, receiveCooldown);
        stage.addActor(receiveCooldownSlider);
    }

    public Slider makeSlider(float min, float max, float stepSize, boolean vertical, float x, float y, Options o) {
        Slider slider = new Slider(min, max, stepSize, vertical, skin);
        slider.setPosition(x, y);
        slider.setSize(sliderWidth, sliderHeight);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                VM.setOption(o, value);
                System.out.println(value);
            }
        });
        return slider;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground(1f);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(previousScreen);
        }
    }

    @Override
    public void resize(int width, int height) { viewport.update(width, height); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }
}
