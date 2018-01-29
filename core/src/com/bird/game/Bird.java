package com.bird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.game.Screens.PlayScreen;

import java.lang.reflect.Method;
import java.util.List;

public class Bird extends Game {
	public SpriteBatch batch;
	public static int Width;
	public static int Height;
	public static final float PPM=2;
	public static final float PPM2=1f;


	public static final short OBJECT_BIT = 32;

//	public int
	public static int[] ourdimensions(){
		Height = Gdx.graphics.getHeight();
		Width = Gdx.graphics.getWidth();
		return new int[] {Width, Height};	};
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

		Gdx.app.log("HEREHERE","HIII");
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
