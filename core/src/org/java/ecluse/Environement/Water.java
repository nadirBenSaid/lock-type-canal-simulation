package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Disposable;

public class Water implements Disposable{

	Texture water;
	TextureRegion waterRegion1;
	TextureRegion waterRegion2;

	Texture surfaceWater;
	TextureRegion surfaceWaterRegion1;
	TextureRegion surfaceWaterRegion2;

	public Water(){
		water = new Texture("Env/18.png");
		water.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		waterRegion1 = new TextureRegion(water);
		waterRegion1.setRegion( 0, 0, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-water.getHeight());
		waterRegion2 = new TextureRegion(water);
		waterRegion2.setRegion( 0, 0, Gdx.graphics.getWidth()/3+1, Gdx.graphics.getHeight()/2-water.getHeight()+90);

		surfaceWater = new Texture("Env/17.png");
		surfaceWater.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);
		surfaceWaterRegion2 = new TextureRegion(surfaceWater);
		surfaceWaterRegion2.setRegion( 0, 0, Gdx.graphics.getWidth()/3*4, water.getHeight());
		surfaceWaterRegion1 = new TextureRegion(surfaceWater);
		surfaceWaterRegion1.setRegion( 0, 0, Gdx.graphics.getWidth()/3*4+4, water.getHeight());

	}

	public void render(SpriteBatch batch){
		batch.draw(waterRegion1, 0, 0);
		batch.draw(waterRegion2, Gdx.graphics.getWidth()*2/3, 0);
		batch.draw(surfaceWaterRegion1, 0, Gdx.graphics.getHeight()/2-water.getHeight(), 0, 0, Gdx.graphics.getWidth()/3*4, 128, 0.25f, 0.25f, 0);
		batch.draw(surfaceWaterRegion2, Gdx.graphics.getWidth()*2/3, Gdx.graphics.getHeight()/2-water.getHeight()+90, 0, 0, Gdx.graphics.getWidth()/3*4+4, 128, 0.25f, 0.25f, 0);
	}

	public void dispose(){
		water.dispose();
		surfaceWater.dispose();
	}

}