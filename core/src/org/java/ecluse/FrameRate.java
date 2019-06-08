package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Color;

public class FrameRate implements Disposable{
	
	long lastTimeCounted;
	private float sinceChange;
	private float frameRate;
	private BitmapFont font;
	private SpriteBatch batch;


	public FrameRate() {
		lastTimeCounted = TimeUtils.millis();
		sinceChange = 0;
		frameRate = Gdx.graphics.getFramesPerSecond();
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	public void update() {
		long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
		lastTimeCounted = TimeUtils.millis();

		sinceChange += delta;
		if(sinceChange >= 1000) {
			sinceChange = 0;
			frameRate = Gdx.graphics.getFramesPerSecond();
		}
	}

	public void render() {
		batch.begin();
		font.setColor(Color.RED);
		font.getData().setScale(3, 3);
		font.draw(batch, (int)frameRate + " fps", 4, Gdx.graphics.getHeight() - 4);
		batch.end();
	}

	public void dispose() {
		font.dispose();
		batch.dispose();
	}
}
