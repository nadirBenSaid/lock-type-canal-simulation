package org.java.ecluse;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ecluse extends ApplicationAdapter {

	SpriteBatch batch;
	FrameRate fps;
	Environement env;

	EcluseController ecluse;
	
	Boat boat;
	
	
	@Override
	public void create () {
		Music music = Gdx.audio.newMusic(Gdx.files.internal("Soundtrack/main_theme.mp3"));
		music.setLooping(true);
		music.setVolume(0.7f);
		batch = new SpriteBatch();
		fps = new FrameRate();
		env = new Environement();
		boat = new Boat();
		ecluse = new EcluseController(boat);
		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.89f, 0.971f, 0.996f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		env.render(batch);
		ecluse.render(batch);
		boat.render(batch);
		batch.end();
		ecluse.shapeRender();
		fps.update();
		fps.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		env.dispose();
		boat.dispose();
		fps.dispose();
	}
}
