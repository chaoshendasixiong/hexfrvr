package com.csdsx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.csdsx.game.util.BaseGame;

public class MyGdxGame extends BaseGame {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;
	Pixmap pixmap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
		pixmap = new Pixmap(Gdx.files.internal("badlogic.jpg"));
		pixmap.setColor(Color.BLUE);
		pixmap.drawRectangle(0, 0, 100, 100);
		img = new Texture(pixmap);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//		shapeRenderer.circle(200,200,50);
//		shapeRenderer.end();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
