package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class EcluseWater extends Movable implements Disposable{

	Texture water;
	TextureRegion waterRegion3;

	Texture surfaceWater;
	TextureRegion surfaceWaterRegion3;

	boolean isTop = false;
	boolean isBottom = false;

	public EcluseWater(){
		super(0, 0.25f, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-128+45, Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2-128, Gdx.graphics.getHeight()/2-128+90);
		water = new Texture("Env/18.png");
		water.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		surfaceWater = new Texture("Env/17.png");
		surfaceWater.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);
		waterRegion3 = new TextureRegion(water);
		waterRegion3.setRegion( 0, 0, Gdx.graphics.getWidth()/3+1, Gdx.graphics.getHeight()/2);
		surfaceWaterRegion3 = new TextureRegion(surfaceWater);
		surfaceWaterRegion3.setRegion( 0, 0, Gdx.graphics.getWidth()/3*4+4, 128);
	}

	public void flagController(){

		if (this.posY == Gdx.graphics.getHeight()/2-water.getHeight()) {
			this.isBottom = true;
		}else if(this.posY == Gdx.graphics.getHeight()/2-water.getHeight()+90){
			this.isTop = true;
		}else{
			this.isTop = false;
			this.isBottom = false;
		}
	}

	public void posController(){
		if (isMovingY) {
			if (goUp) {
				this.moveUp();
			}else{
				this.moveDown();
			}	
		}
	}

	public void stateController(){

	}

	public void render(SpriteBatch batch){
		this.flagController();
		this.posController();
		this.stateController();
		batch.draw(waterRegion3, this.posX, this.posY-Gdx.graphics.getHeight()/2);
		batch.draw(surfaceWaterRegion3, this.posX , this.posY, 0, 0, Gdx.graphics.getWidth()/3*4+4, 128, 0.25f, 0.25f, 0);
	}

	public void dispose(){
		water.dispose();
		surfaceWater.dispose();
	}
}