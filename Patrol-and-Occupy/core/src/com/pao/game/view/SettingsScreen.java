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
import com.pao.game.Constants.ModelConstants;
import com.pao.game.Constants.ViewConstants;
import com.pao.game.communication.Options;
import com.pao.game.viewmodel.EditSettings;

import static com.pao.game.communication.Options.*;

public class SettingsScreen implements Screen {

    final float RESET_BUTTON_WIDTH = ViewConstants.getFloatConstant("SettingsScreen.Reset.Button.Width");
    final float RESET_BUTTON_HEIGHT = ViewConstants.getFloatConstant("SettingsScreen.Reset.Button.Height");
    final float RESET_BUTTON_Y = ViewConstants.getFloatConstant("SettingsScreen.Reset.Button.Y");
    final float SLIDER_WIDTH = ViewConstants.getFloatConstant("SettingsScreen.Slider.Width");
    final float SLIDER_HEIGHT = ViewConstants.getFloatConstant("SettingsScreen.Slider.Height");
    final float SLIDER_TEXT_OFF = ViewConstants.getFloatConstant("SettingsScreen.Slider.Text.Off");
    final float MIN_VALUE_X_OFF = ViewConstants.getFloatConstant("SettingsScreen.Min.Value.X.Off");
    final float MIN_VALUE_Y_OFF = ViewConstants.getFloatConstant("SettingsScreen.Min.Value.Y.Off");
    final float MAX_VALUE_X_OFF = ViewConstants.getFloatConstant("SettingsScreen.Max.Value.X.Off");
    final float MAX_VALUE_Y_OFF = ViewConstants.getFloatConstant("SettingsScreen.Max.Value.Y.Off");
    final float ROW1_Y = ViewConstants.getFloatConstant("SettingsScreen.ROW1.Y");
    final float ROW2_Y = ViewConstants.getFloatConstant("SettingsScreen.ROW2.Y");
    final float ROW3_Y = ViewConstants.getFloatConstant("SettingsScreen.ROW3.Y");
    final float ROW4_Y = ViewConstants.getFloatConstant("SettingsScreen.ROW4.Y");
    final float ROW5_Y = ViewConstants.getFloatConstant("SettingsScreen.ROW5.Y");
    final float BACKGROUND_R = ViewConstants.getFloatConstant("SettingsScreen.BackGround.R");
    final float BACKGROUND_G = ViewConstants.getFloatConstant("SettingsScreen.BackGround.G");
    final float BACKGROUND_B = ViewConstants.getFloatConstant("SettingsScreen.BackGround.B");
    final float BACKGROUND_A = ViewConstants.getFloatConstant("SettingsScreen.BackGround.A");

    Drop game;
    OrthographicCamera camera;
    Viewport viewport;
    Vector3 touchPoint;
    RegionPainter painter;
    Stage stage;
    Skin skin;
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
        painter = new RegionPainter(game.batch,0,0,Drop.WIDTH,Drop.HEIGHT,Drop.WIDTH,Drop.HEIGHT,new Color(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A));
        painter.addFont("Suwaki", 20, Color.WHITE);

        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal(ViewConstants.getConstant("SettingsScreen.Skin")));

        tankSpeedSlider = makeSlider(ModelConstants.getConstant("min.TankSpeed"),
                ModelConstants.getConstant("max.TankSpeed"),
                ModelConstants.getConstant("step.TankSpeed"),
                false, Drop.WIDTH/2 - SLIDER_WIDTH *3/2, ROW1_Y, tankSpeed);
        tankSpeedSlider.setValue(EditSettings.getTankSpeed());
        stage.addActor(tankSpeedSlider);

        rotateSpeedSlider = makeSlider(ModelConstants.getConstant("min.RotateSpeed"),
                ModelConstants.getConstant("max.RotateSpeed"),
                ModelConstants.getConstant("step.RotateSpeed"),
                false, Drop.WIDTH/2 + SLIDER_WIDTH /2, ROW1_Y, rotateSpeed);
        rotateSpeedSlider.setValue(EditSettings.getRotateSpeed());
        stage.addActor(rotateSpeedSlider);

        bulletSpeedSlider = makeSlider(ModelConstants.getConstant("min.BulletSpeed"),
                ModelConstants.getConstant("max.BulletSpeed"),
                ModelConstants.getConstant("step.BulletSpeed"),
                false, Drop.WIDTH/2 - SLIDER_WIDTH *3/2, ROW2_Y, bulletSpeed);
        bulletSpeedSlider.setValue(EditSettings.getBulletSpeed());
        stage.addActor(bulletSpeedSlider);

        magazineCapacitySlider = makeSlider(ModelConstants.getConstant("min.MagazineCapacity"),
                ModelConstants.getConstant("max.MagazineCapacity"),
                ModelConstants.getConstant("step.MagazineCapacity"),
                false, Drop.WIDTH/2 + SLIDER_WIDTH /2, ROW2_Y, magazineCapacity);
        magazineCapacitySlider.setValue(EditSettings.getMagazineCapacity());
        stage.addActor(magazineCapacitySlider);
        shootCooldownSlider = makeSlider(ModelConstants.getConstant("min.ShootCooldown"),
                ModelConstants.getConstant("max.ShootCooldown"),
                ModelConstants.getConstant("step.ShootCooldown"),
                false, Drop.WIDTH/2 - SLIDER_WIDTH *3/2, ROW3_Y, shootCooldown);
        shootCooldownSlider.setValue(EditSettings.getShootCooldown());
        stage.addActor(shootCooldownSlider);

        receiveCooldownSlider = makeSlider(ModelConstants.getConstant("min.ReceiveCooldown"),
                ModelConstants.getConstant("max.ReceiveCooldown"),
                ModelConstants.getConstant("step.ReceiveCooldown"),
                false, Drop.WIDTH/2 + SLIDER_WIDTH /2, ROW3_Y, receiveCooldown);
        receiveCooldownSlider.setValue(EditSettings.getReceiveCooldown());
        stage.addActor(receiveCooldownSlider);

        gameTimeSlider = makeSlider(ModelConstants.getConstant("min.GameTime"),
                ModelConstants.getConstant("max.GameTime"),
                ModelConstants.getConstant("step.GameTime"),
                false, Drop.WIDTH/2 - SLIDER_WIDTH *3/2, ROW4_Y, gameTime);
        gameTimeSlider.setValue(EditSettings.getGameTime());
        stage.addActor(gameTimeSlider);

        respawnSlider = makeSlider(ModelConstants.getConstant("min.RespawnCooldown"),
                ModelConstants.getConstant("max.RespawnCooldown"),
                ModelConstants.getConstant("step.RespawnCooldown"),
                false, Drop.WIDTH/2 + SLIDER_WIDTH /2, ROW4_Y, respawnCooldown);
        respawnSlider.setValue(EditSettings.getRespawnCooldown());
        stage.addActor(respawnSlider);

        dynamiteCoolDownSlider = makeSlider(ModelConstants.getConstant("min.DynamiteCooldown"),
                ModelConstants.getConstant("max.DynamiteCooldown"),
                ModelConstants.getConstant("step.DynamiteCooldown"),
                false, Drop.WIDTH/2 - SLIDER_WIDTH /2 , ROW5_Y, dynamiteCooldown);
        dynamiteCoolDownSlider.setValue(EditSettings.getDynamiteCooldown());
        stage.addActor(dynamiteCoolDownSlider);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont(ViewConstants.getConstant("SettingsScreen.default-font"));
        textButtonStyle.up = skin.getDrawable(ViewConstants.getConstant("SettingsScreen.default-rect"));
        textButtonStyle.down = skin.getDrawable(ViewConstants.getConstant("SettingsScreen.default-rect-down"));

        TextButton button = new TextButton("Reset", skin);
        button.setSize(RESET_BUTTON_WIDTH, RESET_BUTTON_HEIGHT);
        button.setPosition(Drop.WIDTH/2 - button.getWidth()/2, RESET_BUTTON_Y);
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
        slider.setSize(SLIDER_WIDTH, SLIDER_HEIGHT);
        Label minValueLabel = new Label(String.valueOf((int)min), skin);
        minValueLabel.setPosition(x - MIN_VALUE_X_OFF, y - MIN_VALUE_Y_OFF);
        stage.addActor(minValueLabel);
        Label maxValueLabel = new Label(String.valueOf((int)max), skin);
        maxValueLabel.setPosition(x + SLIDER_WIDTH - MAX_VALUE_X_OFF, y - MAX_VALUE_Y_OFF);
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
        ScreenUtils.clear(BACKGROUND_R,BACKGROUND_G,BACKGROUND_B,BACKGROUND_A);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        painter.fillBackground();

        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.tankSpeedSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.tankSpeedSlider.Format"),
                                tankSpeedSlider.getValue()),
                tankSpeedSlider.getX()+tankSpeedSlider.getWidth()/2,
                tankSpeedSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.rotateSpeedSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.rotateSpeedSlider.Format"),
                                rotateSpeedSlider.getValue()),
                rotateSpeedSlider.getX()+rotateSpeedSlider.getWidth()/2,
                rotateSpeedSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.bulletSpeedSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.bulletSpeedSlider.Format"),
                                bulletSpeedSlider.getValue()),
                bulletSpeedSlider.getX()+bulletSpeedSlider.getWidth()/2,
                bulletSpeedSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.magazineCapacitySlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.magazineCapacitySlider.Format"),
                                magazineCapacitySlider.getValue()),
                magazineCapacitySlider.getX()+magazineCapacitySlider.getWidth()/2,
                magazineCapacitySlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.shootCooldownSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.shootCooldownSlider.Format"),
                                shootCooldownSlider.getValue()),
                shootCooldownSlider.getX()+shootCooldownSlider.getWidth()/2,
                shootCooldownSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.receiveCooldownSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.receiveCooldownSlider.Format"),
                                receiveCooldownSlider.getValue()),
                receiveCooldownSlider.getX()+receiveCooldownSlider.getWidth()/2,
                receiveCooldownSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.gameTimeSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.gameTimeSlider.Format"),
                                gameTimeSlider.getValue()),
                gameTimeSlider.getX()+gameTimeSlider.getWidth()/2,
                gameTimeSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.respawnSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.respawnSlider.Format"),
                                respawnSlider.getValue()),
                respawnSlider.getX()+respawnSlider.getWidth()/2,
                respawnSlider.getY() + SLIDER_TEXT_OFF);
        painter.drowWriting("Suwaki",
                ViewConstants.getConstant("SettingsScreen.dynamiteCoolDownSlider.Text") +
                        String.format(ViewConstants.getConstant("SettingsScreen.dynamiteCoolDownSlider.Format"),
                                dynamiteCoolDownSlider.getValue()),
                dynamiteCoolDownSlider.getX()+ dynamiteCoolDownSlider.getWidth()/2,
                dynamiteCoolDownSlider.getY() + SLIDER_TEXT_OFF);

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
