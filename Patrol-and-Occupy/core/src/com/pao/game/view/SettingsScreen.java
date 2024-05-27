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
    Slider gameTimeSlider;

    public SettingsScreen(Drop game) {
        this.game = game;
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

        tankSpeedSlider = makeSlider(1, 20, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 700f, tankSpeed);
        tankSpeedSlider.setValue(EditSettings.getTankSpeed());
        stage.addActor(tankSpeedSlider);
        bulletSpeedSlider = makeSlider(1, 40, 1, false, Drop.WIDTH/2 + sliderWidth/2, 700f, bulletSpeed);
        bulletSpeedSlider.setValue(EditSettings.getBulletSpeed());
        stage.addActor(bulletSpeedSlider);
        magazineCapacitySlider = makeSlider(1, 10, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 550f, magazineCapacity);
        magazineCapacitySlider.setValue(EditSettings.getMagazineCapacity());
        stage.addActor(magazineCapacitySlider);
        shootCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 + sliderWidth/2, 550f, shootCooldown);
        shootCooldownSlider.setValue(EditSettings.getShootCooldown());
        stage.addActor(shootCooldownSlider);
        receiveCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 - sliderWidth*3/2, 400f, receiveCooldown);
        receiveCooldownSlider.setValue(EditSettings.getReceiveCooldown());
        stage.addActor(receiveCooldownSlider);
        gameTimeSlider = makeSlider(10, 200, 10f, false, Drop.WIDTH/2 + sliderWidth/2, 400f, gameTime);
        gameTimeSlider.setValue(EditSettings.getGameTime());
        stage.addActor(gameTimeSlider);

    }

    public Slider makeSlider(float min, float max, float stepSize, boolean vertical, float x, float y, Options o) {
        Slider slider = new Slider(min, max, stepSize, vertical, skin);
        slider.setPosition(x, y);
        slider.setSize(sliderWidth, sliderHeight);
        Label minValueLabel = new Label(String.valueOf((int)min), skin);
        minValueLabel.setPosition(x - 3, y - 25);
        stage.addActor(minValueLabel);
        Label maxValueLabel = new Label(String.valueOf((int)max), skin);
        maxValueLabel.setPosition(x + sliderWidth - 15, y - 25);
        stage.addActor(maxValueLabel);
        //slider.setValue(slider.getMinValue() + (slider.getMaxValue() - slider.getMinValue()) / 2);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                EditSettings.setOption(o, value);
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
        font.draw(game.batch, "Tank speed: " + String.format("%.0f",tankSpeedSlider.getValue()), tankSpeedSlider.getX(), tankSpeedSlider.getY() + 50);
        font.draw(game.batch, "Bullet speed: " + String.format("%.0f",bulletSpeedSlider.getValue()), bulletSpeedSlider.getX(), bulletSpeedSlider.getY() + 50);
        font.draw(game.batch, "Magazine capacity: " + String.format("%.0f",magazineCapacitySlider.getValue()), magazineCapacitySlider.getX(), magazineCapacitySlider.getY() + 50);
        font.draw(game.batch, "Shoot cool down: " + String.format("%.1f",shootCooldownSlider.getValue()), shootCooldownSlider.getX(), shootCooldownSlider.getY() + 50);
        font.draw(game.batch, "Reload time: " + String.format("%.1f",receiveCooldownSlider.getValue()), receiveCooldownSlider.getX(), receiveCooldownSlider.getY() + 50);
        font.draw(game.batch, "Game time: " + String.format("%.0f",gameTimeSlider.getValue()), gameTimeSlider.getX(), gameTimeSlider.getY() + 50);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
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
