package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Disposable;

public class Land implements Disposable{

	Texture shoreLeft;
	TextureRegion shoreLeftRegion;
	Texture shoreMiddle;
	TextureRegion shoreMiddleRegion;
	Texture shoreRight;
	TextureRegion shoreRightRegion;
	Texture depthLeft;
	TextureRegion depthLeftRegion;
	Texture depthMiddle;
	TextureRegion depthMiddleRegion;
	Texture depthRight;
	TextureRegion depthRightRegion;

	public Land(){
		shoreLeft = new Texture("Env/1.png");
		shoreLeftRegion = new TextureRegion(shoreLeft);
		shoreLeftRegion.setRegion( 0, 0, shoreLeft.getWidth(), shoreLeft.getHeight());
		shoreMiddle = new Texture("Env/2.png");
		shoreMiddle.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		shoreMiddleRegion = new TextureRegion(shoreMiddle);
		shoreMiddleRegion.setRegion( 0, 0, Gdx.graphics.getWidth()-2*shoreMiddle.getWidth(), shoreMiddle.getHeight());
		shoreRight = new Texture("Env/3.png");
		shoreRightRegion = new TextureRegion(shoreRight);
		shoreRightRegion.setRegion( 0, 0, shoreRight.getWidth(), shoreRight.getHeight());
		depthLeft = new Texture("Env/4.png");
		depthLeft.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		depthLeftRegion = new TextureRegion(depthLeft);
		depthLeftRegion.setRegion( 0, 0, depthLeft.getWidth(), (1+Gdx.graphics.getHeight()/(2*depthLeft.getHeight()))*depthLeft.getHeight());
		depthMiddle = new Texture("Env/5.png");
		depthMiddle.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		depthMiddleRegion = new TextureRegion(depthMiddle);
		depthMiddleRegion.setRegion( 0, 0, Gdx.graphics.getWidth()-2*depthMiddle.getWidth(), (1+Gdx.graphics.getHeight()/(2*depthMiddle.getHeight()))*depthMiddle.getHeight());
		depthRight = new Texture("Env/6.png");
		depthRight.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		depthRightRegion = new TextureRegion(depthRight);
		depthRightRegion.setRegion( 0, 0, depthRight.getWidth(), (1+Gdx.graphics.getHeight()/(2*depthRight.getHeight()))*depthRight.getHeight());
	}

	public void render(SpriteBatch batch){
		batch.draw(shoreLeftRegion, 0, Gdx.graphics.getHeight()/2-shoreLeft.getHeight()/2);
		batch.draw(shoreMiddleRegion, shoreMiddle.getWidth(), Gdx.graphics.getHeight()/2-shoreMiddle.getHeight()/2);
		batch.draw(shoreRightRegion, Gdx.graphics.getWidth()-shoreRight.getWidth(), Gdx.graphics.getHeight()/2-shoreRight.getHeight()/2);
		batch.draw(depthRightRegion, Gdx.graphics.getWidth()-depthRight.getWidth(), Gdx.graphics.getHeight()/2-depthRight.getHeight()/2-(1+Gdx.graphics.getHeight()/(2*depthRight.getHeight()))*depthRight.getHeight());
		batch.draw(depthMiddleRegion, depthMiddle.getWidth(), Gdx.graphics.getHeight()/2-depthMiddle.getHeight()/2-(1+Gdx.graphics.getHeight()/(2*depthMiddle.getHeight()))*depthMiddle.getHeight());
		batch.draw(depthLeftRegion, 0, Gdx.graphics.getHeight()/2-depthLeft.getHeight()/2-(1+Gdx.graphics.getHeight()/(2*depthLeft.getHeight()))*depthLeft.getHeight());
	}


	public void dispose(){
		shoreLeft.dispose();
		shoreMiddle.dispose();
		shoreRight.dispose();
		depthLeft.dispose();
		depthMiddle.dispose();
		depthRight.dispose();
	}

}