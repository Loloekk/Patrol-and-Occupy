package com.pao.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class PatrolandOccupy implements Screen{

	final Drop game;
	private OrthographicCamera camera;
	private Texture player1Texture;
	private Texture player2Texture;
	private Rectangle player1;
	private Rectangle player2;
	private static final int PLAYER_SPEED = 200;
	private static final int BULLET_SPEED = 500;
	private Texture bulletTexture;
	private Rectangle bullet1;
	private Rectangle bullet2;
	private boolean shoot1 = false;
	private boolean shoot2 = false;

	public PatrolandOccupy (final Drop game) {
		this.game=game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		player1Texture = new Texture(Gdx.files.internal("player1.png"));
		player2Texture = new Texture(Gdx.files.internal("player2.jpg"));
		bulletTexture = new Texture(Gdx.files.internal("bullet.png"));

		player1 = new Rectangle();
		player1.width = 64;
		player1.height = 64;
		player1.x = 50;
		player1.y = 200;

		player2 = new Rectangle();
		player2.width = 64;
		player2.height = 64;
		player2.x = 700;
		player2.y = 200;

		bullet1 = new Rectangle();
		bullet1.width = 16;
		bullet1.height = 16;

		bullet2 = new Rectangle();
		bullet2.width = 16;
		bullet2.height = 16;
	}
	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(player1Texture, player1.x, player1.y);
		game.batch.draw(player2Texture, player2.x, player2.y);
		if (shoot1) game.batch.draw(bulletTexture, bullet1.x, bullet1.y);
		if (shoot2) game.batch.draw(bulletTexture, bullet2.x, bullet2.y);
		game.batch.end();

		if(Gdx.input.isKeyPressed(29)) player1.x -= PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.D)) player1.x += PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.W)) player1.y += PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) player1.y -= PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			shoot1 = true;
			bullet1.x = player1.x + 32;
			bullet1.y = player1.y + 32;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player2.x -= PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player2.x += PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) player2.y += PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player2.y -= PLAYER_SPEED * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
			shoot2 = true;
			bullet2.x = player2.x + 32;
			bullet2.y = player2.y + 32;
		}

		if (shoot1) {
			bullet1.x += BULLET_SPEED * Gdx.graphics.getDeltaTime();
			if (bullet1.x > 800) shoot1 = false;
			if (bullet1.overlaps(player2)) {
				shoot1 = false;
				bullet1.x = -100;
				bullet1.y = -100;
			}
		}

		if (shoot2) {
			bullet2.x -= BULLET_SPEED * Gdx.graphics.getDeltaTime();
			if (bullet2.x < 0) shoot2 = false;
			if (bullet2.overlaps(player1)) {
				shoot2 = false;
				bullet2.x = -100;
				bullet2.y = -100;
			}
		}
	}
	@Override
	public void resize(int width, int height) {
		// Metoda wywoływana po zmianie rozmiaru ekranu
	}

	@Override
	public void show() {
		//
	}

	@Override
	public void hide() {
		// Metoda wywoływana gdy ten ekran zostanie ukryty
	}

	@Override
	public void pause() {
		// Metoda wywoływana gdy gra jest zatrzymana
	}

	@Override
	public void resume() {
		// Metoda wywoływana gdy gra jest wznowiona
	}
	@Override
	public void dispose () {
		game.batch.dispose();
		player1Texture.dispose();
		player2Texture.dispose();
		bulletTexture.dispose();
	}
}