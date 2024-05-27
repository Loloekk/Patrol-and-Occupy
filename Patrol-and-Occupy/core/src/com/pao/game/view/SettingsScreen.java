package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.model.Options;
import com.pao.game.viewmodel.EditSettings;
import com.pao.game.viewmodel.ViewModel;

import static com.pao.game.model.Constants.PPM;
import static com.pao.game.model.Options.*;

public class SettingsScreen implements Screen {
    Drop game;
    OrthographicCamera camera;
    EditSettings ES;
    Viewport viewport;
    Vector3 touchPoint;
    RegionPainter painter;
    BitmapFont font;
    Stage stage;
    Skin skin;
    float sliderWidth = 200f;
    float sliderHeight = 20f;
    Slider tankSpeedSlider;
    Slider bulletSpeedSlider;
    Slider magazineCapacitySlider;
    Slider shootCooldownSlider;
    Slider receiveCooldownSlider;

    public SettingsScreen(Drop game, EditSettings ES) {
        this.game = game;
        this.ES = ES;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        tankSpeedSlider = makeSlider(50, 1000, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 700f, tankSpeed);
        tankSpeedSlider.setValue(ES.getTankSpeed());
        stage.addActor(tankSpeedSlider);
        bulletSpeedSlider = makeSlider(400, 1000, 50, false, Drop.WIDTH/2 + sliderWidth/2, 700f, bulletSpeed);
        bulletSpeedSlider.setValue(ES.getBulletSpeed());
        stage.addActor(bulletSpeedSlider);
        magazineCapacitySlider = makeSlider(1, 11, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 550f, magazineCapacity);
        magazineCapacitySlider.setValue(ES.getMagazineCapacity());
        stage.addActor(magazineCapacitySlider);
        shootCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 + sliderWidth/2, 550f, shootCooldown);
        shootCooldownSlider.setValue(ES.getShootCooldown());
        stage.addActor(shootCooldownSlider);
        receiveCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 - sliderWidth*3/2, 400f, receiveCooldown);
        receiveCooldownSlider.setValue(ES.getReceiveCooldown());
        stage.addActor(receiveCooldownSlider);

    }

    public Slider makeSlider(float min, float max, float stepSize, boolean vertical, float x, float y, Options o) {
        Slider slider = new Slider(min, max, stepSize, vertical, skin);
        slider.setPosition(x, y);
        slider.setSize(sliderWidth, sliderHeight);
        Label minValueLabel = new Label("0", skin);
        minValueLabel.setPosition(x - 3, y - 25);
        stage.addActor(minValueLabel);
        Label maxValueLabel = new Label("100", skin);
        maxValueLabel.setPosition(x + sliderWidth - 25, y - 25);
        stage.addActor(maxValueLabel);
        //slider.setValue(slider.getMinValue() + (slider.getMaxValue() - slider.getMinValue()) / 2);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                ES.setOption(o, value);
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
        font.draw(game.batch, "Tank speed: " + tankSpeedSlider.getValue(), tankSpeedSlider.getX(), tankSpeedSlider.getY() + 50);
        font.draw(game.batch, "Bullet speed: " + bulletSpeedSlider.getValue(), bulletSpeedSlider.getX(), bulletSpeedSlider.getY() + 50);
        font.draw(game.batch, "Magazine capacity: " + magazineCapacitySlider.getValue(), magazineCapacitySlider.getX(), magazineCapacitySlider.getY() + 50);
        font.draw(game.batch, "Shoot cool down: " + shootCooldownSlider.getValue(), shootCooldownSlider.getX(), shootCooldownSlider.getY() + 50);
        font.draw(game.batch, "Reload time: " + receiveCooldownSlider.getValue(), receiveCooldownSlider.getX(), receiveCooldownSlider.getY() + 50);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game, ES));
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
