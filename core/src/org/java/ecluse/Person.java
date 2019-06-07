package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Person extends Movable implements Disposable{

	int IDLE_FRAME_COLS, IDLE_FRAME_ROWS;
	int WALK_FRAME_COLS, WALK_FRAME_ROWS;
	int JUMP_FRAME_COLS, JUMP_FRAME_ROWS;

	Animation<TextureRegion> idleAnimation;
	Animation<TextureRegion> walkAnimation;
	Animation<TextureRegion> jumpAnimation;

	TextureRegion currentFrame;

	Texture idleSheet;
	Texture jumpSheet;
	Texture walkSheet;

	float stateTime;

	// boolean inEcluse = false;
	// boolean nearEcluse = false;

	public Person(String type, int a, int b, int c, int d, int e, int f) {
		super(1.5f, 0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+48, -100, Gdx.graphics.getWidth(), 0, 0);
		IDLE_FRAME_COLS = a;
		IDLE_FRAME_ROWS = b;
		WALK_FRAME_COLS = c;
		WALK_FRAME_ROWS = d;
		JUMP_FRAME_COLS = e;
		JUMP_FRAME_ROWS = f;
		this.idleAnimation(type);
		this.walkAnimation(type);
		this.jumpAnimation(type);
	}

	public void idleAnimation(String type){
		idleSheet = new Texture("Animation/"+type+"_idle.png");
		TextureRegion[][] tmp = TextureRegion.split(idleSheet, 
			idleSheet.getWidth() / this.IDLE_FRAME_COLS,
			idleSheet.getHeight() / this.IDLE_FRAME_ROWS);
		TextureRegion[] idleFrames = new TextureRegion[this.IDLE_FRAME_COLS*this.IDLE_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < this.IDLE_FRAME_ROWS; i++) {
			for (int j = 0; j < this.IDLE_FRAME_COLS; j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}
		idleAnimation = new Animation<TextureRegion>(1f/(this.IDLE_FRAME_COLS*this.IDLE_FRAME_ROWS), idleFrames);
		stateTime = 0f;
	}

	public void walkAnimation(String type){
		walkSheet = new Texture("Animation/"+type+"_walk.png");
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
			walkSheet.getWidth() / this.WALK_FRAME_COLS,
			walkSheet.getHeight() / this.WALK_FRAME_ROWS);
		TextureRegion[] walkFrames = new TextureRegion[this.WALK_FRAME_COLS*this.WALK_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < this.WALK_FRAME_ROWS; i++) {
			for (int j = 0; j < this.WALK_FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation<TextureRegion>(1f/(this.WALK_FRAME_COLS*this.WALK_FRAME_ROWS), walkFrames);
		stateTime = 0f;
	}

	public void jumpAnimation(String type){
		jumpSheet = new Texture("Animation/"+type+"_jump.png");
		TextureRegion[][] tmp = TextureRegion.split(jumpSheet, 
			jumpSheet.getWidth() / this.JUMP_FRAME_COLS,
			jumpSheet.getHeight() / this.JUMP_FRAME_ROWS);
		TextureRegion[] jumpFrames = new TextureRegion[this.JUMP_FRAME_COLS*this.JUMP_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < this.JUMP_FRAME_ROWS; i++) {
			for (int j = 0; j < this.JUMP_FRAME_COLS; j++) {
				jumpFrames[index++] = tmp[i][j];
			}
		}
		jumpAnimation = new Animation<TextureRegion>(1f/(this.JUMP_FRAME_COLS*this.JUMP_FRAME_ROWS), jumpFrames);
		stateTime = 0f;
	}

	public void flagController(){
		int loop = (int) stateTime%40;
		if (loop>0 && loop<=15) {
			isMovingX = true;
			speedX = 1;
		}else if(loop > 20 && loop < 35) {
			isMovingX = true;
			speedX = 1.5f;
		}else{
			isMovingX = false;
		}

		if (this.posX<=this.trajectoryMinX) { 
			this.goRight=true;
		}else if(this.posX>=this.trajectoryMaxX){
			this.goRight=false;
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

				this.moveDown();
			}	
		}
	}

	public void stateController(){
		stateTime += Gdx.graphics.getDeltaTime();
		if (!isMovingX) {
			currentFrame = idleAnimation.getKeyFrame(stateTime, true);	
		}else{
			if (speedX == 1) {
				currentFrame = walkAnimation.getKeyFrame(stateTime, true);
			}else{
				currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
			}
		}
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
		batch.draw(currentFrame, this.posX , this.posY, 0, 0, 614f, 564f, 0.25f, 0.25f, 0);
	}

	public void dispose() {
		idleSheet.dispose();
		jumpSheet.dispose();
		walkSheet.dispose();
	}
}