package com.pao.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pao.game.communication.Options;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.*;

public class SettingsScreen implements Screen {
    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Vector3 touchPoint;
    RegionPainter painter;
    Stage stage;
    Skin skin;
    float sliderWidth = 200f;
    float sliderHeight = 20f;
    Slider tankSpeedSlider;
    Slider rotateSpeedSlider;
    Slider bulletSpeedSlider;
    Slider magazineCapacitySlider;
    Slider shootCooldownSlider;
    Slider receiveCooldownSlider;
    Slider gameTimeSlider;
    Slider respawnSlider;

    Slider dynamiteCoolDownSlider;

    public SettingsScreen(Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Drop.WIDTH, Drop.HEIGHT);
        viewport = new ExtendViewport(Drop.WIDTH, Drop.HEIGHT, camera);
        touchPoint = new Vector3();
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(0.5f,0.4f,0.4f,1));
        painter.addFont("Suwaki", 20, Color.WHITE);

        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        tankSpeedSlider = makeSlider(1, 20, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 850f, tankSpeed);
        tankSpeedSlider.setValue(EditSettings.getTankSpeed());
        stage.addActor(tankSpeedSlider);
        rotateSpeedSlider = makeSlider(1, 10, 0.5f, false, Drop.WIDTH/2 + sliderWidth/2, 850f, rotateSpeed);
        rotateSpeedSlider.setValue(EditSettings.getRotateSpeed());
        stage.addActor(rotateSpeedSlider);
        bulletSpeedSlider = makeSlider(1, 40, 1, false, Drop.WIDTH/2 - sliderWidth*3/2, 700f, bulletSpeed);
        bulletSpeedSlider.setValue(EditSettings.getBulletSpeed());
        stage.addActor(bulletSpeedSlider);
        magazineCapacitySlider = makeSlider(1, 10, 1, false, Drop.WIDTH/2 + sliderWidth/2, 700f, magazineCapacity);
        magazineCapacitySlider.setValue(EditSettings.getMagazineCapacity());
        stage.addActor(magazineCapacitySlider);
        shootCooldownSlider = makeSlider(0, 5, 0.1f, false, Drop.WIDTH/2 - sliderWidth*3/2, 550f, shootCooldown);
        shootCooldownSlider.setValue(EditSettings.getShootCooldown());
        stage.addActor(shootCooldownSlider);
        receiveCooldownSlider = makeSlider(0, 5, 0.5f, false, Drop.WIDTH/2 + sliderWidth/2, 550f, receiveCooldown);
        receiveCooldownSlider.setValue(EditSettings.getReceiveCooldown());
        stage.addActor(receiveCooldownSlider);
        gameTimeSlider = makeSlider(10, 200, 10f, false, Drop.WIDTH/2 - sliderWidth*3/2, 400f, gameTime);
        gameTimeSlider.setValue(EditSettings.getGameTime());
        stage.addActor(gameTimeSlider);
        respawnSlider = makeSlider(0, 20, 1, false, Drop.WIDTH/2 + sliderWidth/2, 400f, respawnCooldown);
        respawnSlider.setValue(EditSettings.getRespawnCooldown());
        stage.addActor(respawnSlider);
        dynamiteCoolDownSlider = makeSlider(0, 20, 0.2f, false, Drop.WIDTH/2 - sliderWidth/2 , 250f, dynamiteCooldown);
        dynamiteCoolDownSlider.setValue(EditSettings.getRespawnCooldown());
        stage.addActor(dynamiteCoolDownSlider);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default-font");
        textButtonStyle.up = skin.getDrawable("default-rect");
        textButtonStyle.down = skin.getDrawable("default-rect-down");

        TextButton button = new TextButton("Reset", skin);
        button.setSize(200, 50);
        button.setPosition(Drop.WIDTH/2 - button.getWidth()/2, 150);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
            }
        });

        stage.addActor(button);
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
    public void reset() {
        EditSettings.setDefault();
        tankSpeedSlider.setValue(EditSettings.getTankSpeed());
        rotateSpeedSlider.setValue(EditSettings.getRotateSpeed());
        bulletSpeedSlider.setValue(EditSettings.getBulletSpeed());
        magazineCapacitySlider.setValue(EditSettings.getMagazineCapacity());
        shootCooldownSlider.setValue(EditSettings.getShootCooldown());
        receiveCooldownSlider.setValue(EditSettings.getReceiveCooldown());
        gameTimeSlider.setValue(EditSettings.getGameTime());
        respawnSlider.setValue(EditSettings.getRespawnCooldown());
        dynamiteCoolDownSlider.setValue(EditSettings.getDynamiteCooldown());
    }
    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground();

        painter.drowWriting("Suwaki", "Tank speed: " + String.format("%.0f",tankSpeedSlider.getValue()), tankSpeedSlider.getX()+tankSpeedSlider.getWidth()/2, tankSpeedSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Rotate speed: " + String.format("%.1f",rotateSpeedSlider.getValue()), rotateSpeedSlider.getX()+rotateSpeedSlider.getWidth()/2, rotateSpeedSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Bullet speed: " + String.format("%.0f",bulletSpeedSlider.getValue()), bulletSpeedSlider.getX()+bulletSpeedSlider.getWidth()/2, bulletSpeedSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Magazine capacity: " + String.format("%.0f",magazineCapacitySlider.getValue()), magazineCapacitySlider.getX()+magazineCapacitySlider.getWidth()/2, magazineCapacitySlider.getY() + 50);
        painter.drowWriting("Suwaki", "Shoot cool down: " + String.format("%.1f",shootCooldownSlider.getValue()), shootCooldownSlider.getX()+shootCooldownSlider.getWidth()/2, shootCooldownSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Reload time: " + String.format("%.1f",receiveCooldownSlider.getValue()), receiveCooldownSlider.getX()+receiveCooldownSlider.getWidth()/2, receiveCooldownSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Game time: " + String.format("%.0f",gameTimeSlider.getValue()), gameTimeSlider.getX()+gameTimeSlider.getWidth()/2, gameTimeSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Respawn cool down: " + String.format("%.0f",respawnSlider.getValue()), respawnSlider.getX()+respawnSlider.getWidth()/2, respawnSlider.getY() + 50);
        painter.drowWriting("Suwaki", "Dynamite cool down: " + String.format("%.1f", dynamiteCoolDownSlider.getValue()), dynamiteCoolDownSlider.getX()+ dynamiteCoolDownSlider.getWidth()/2, dynamiteCoolDownSlider.getY() + 50);

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
    public void hide() { Gdx.input.setInputProcessor(null);}

    @Override
    public void dispose() {

    }
}
