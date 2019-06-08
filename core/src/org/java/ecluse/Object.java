package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Disposable;

public class Object implements Disposable{

	Texture object;
	TextureRegion objectRegion;

	public Object(String texture){
		object = new Texture("Env/"+texture);
		objectRegion = new TextureRegion(object);
		objectRegion.setRegion( 0, 0, object.getWidth(), object.getHeight());
	}

	public void render(SpriteBatch batch, float posX, float posY){
		batch.draw(objectRegion, posX, posY);
	}

	public void dispose(){
		object.dispose();
	}

}