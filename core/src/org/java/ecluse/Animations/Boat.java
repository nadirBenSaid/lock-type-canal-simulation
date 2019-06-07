package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Boat extends Movable implements Disposable{

	private static final int FRAME_COLS = 11, FRAME_ROWS = 14;

	Animation<TextureRegion> boatAnimation;
	TextureRegion currentFrame;
	Texture boatSheet;
	float stateTime;

	boolean inEcluse = false;
	boolean nearEcluse = false;

	public Boat() {
		super(3f, 0.25f, -350, Gdx.graphics.getHeight()/2-200, -350, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2-200, Gdx.graphics.getHeight()/2-200+90);
		boatSheet = new Texture("Animation/boat.png");
		TextureRegion[][] tmp = TextureRegion.split(boatSheet, 
			boatSheet.getWidth() / FRAME_COLS,
			boatSheet.getHeight() / FRAME_ROWS);
		TextureRegion[] boatFrames = new TextureRegion[150];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i==13 && j>6) {}
				else{
					boatFrames[index++] = tmp[i][j];
				}
			}
		}
		boatAnimation = new Animation<TextureRegion>(3f/150, boatFrames);
		stateTime = 0f;

	}

	public void flagController(){

		if (this.posX<=this.trajectoryMinX) { 
			this.goRight=true;
		}else if(this.posX>=this.trajectoryMaxX){
			this.goRight=false;
		}

		if (this.posY == Gdx.graphics.getHeight()/2-128) {
			this.isBottom = true;
		}else if(this.posY == Gdx.graphics.getHeight()/2-128+90){
			this.isTop = true;
		}else{
			this.isTop = false;
			this.isBottom = false;
		}

	}

	public void posController(){
		if (isMovingX) {
			if (goRight) {
				this.moveRight();
			}else{
				this.moveLeft();
			}	
		}
		if (isMovingY) {
			if (goUp) {
				this.moveUp();
			}else{
				this.moveDown();
			}	
		}
	}

	public void stateController(){
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = boatAnimation.getKeyFrame(stateTime, true);
		if (goRight) {
			if (currentFrame.isFlipX()) {
				currentFrame.flip(true, false);				
			}
		}else{
			if (!currentFrame.isFlipX()) {
				currentFrame.flip(true, false);				
			}
		}
	}

	public void render(SpriteBatch batch){
		this.flagController();
		this.posController();
		this.stateController();
		batch.draw(currentFrame, this.posX , this.posY, 0, 0, 1000f, 800f, 0.35f, 0.35f, 0);
	}

	public void dispose() {
		boatSheet.dispose();
	}
}