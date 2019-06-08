package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Disposable;

public class Background implements Disposable{

	Texture background;
	TextureRegion backgroundRegion;

	public Background(){
		background = new Texture("Env/BG.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		backgroundRegion = new TextureRegion(background);
		backgroundRegion.setRegion( 0, 0, Gdx.graphics.getWidth(), background.getHeight());
	}

	public void render(SpriteBatch batch){
		batch.draw(backgroundRegion, 0, Gdx.graphics.getHeight()/2);
	}

	public void dispose(){
		background.dispose();
	}

}