package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.Application.ApplicationType;

public class Girl extends Person implements Disposable{

		static int IDLE_FRAME_COLS = 4, IDLE_FRAME_ROWS = 4;
		static int WALK_FRAME_COLS = 4, WALK_FRAME_ROWS = 5;
		static int JUMP_FRAME_COLS = 5, JUMP_FRAME_ROWS = 6;

		boolean left;
		boolean right;

	public Girl(){
		super("girl", IDLE_FRAME_COLS, IDLE_FRAME_ROWS, WALK_FRAME_COLS, WALK_FRAME_ROWS, JUMP_FRAME_COLS, JUMP_FRAME_ROWS);
		this.speedX = 2f;
		this.goRight = false;
	}

	@Override
	public void flagController(){
		if (!(Gdx.app.getType() == ApplicationType.Android)) {
			left = Gdx.input.isKeyPressed(Keys.LEFT);
			right = Gdx.input.isKeyPressed(Keys.RIGHT);

			if (right) { 
				this.isMovingX = true;
				this.goRight=true;
			}else if(left){
				this.isMovingX = true;
				this.goRight=false;
			}else{
				this.isMovingX = false;
			}	
		}else{
			int loop = (int) stateTime%40;
			if (loop>0 && loop<=15) {
				isMovingX = true;
				speedX = 1;
			}else if(loop > 20 && loop < 25) {
				isMovingX = true;
				speedX = 2f;
			}else{
				isMovingX = false;
			}

			if (this.posX<=this.trajectoryMinX) { 
				this.goRight=true;
			}else if(this.posX>=this.trajectoryMaxX){
				this.goRight=false;
			}
		}
	}

	@Override
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


	@Override
	public void render(SpriteBatch batch){
		this.flagController();
		this.posController();
		this.stateController();
		batch.draw(currentFrame, this.posX , this.posY-2, 0, 0, 416f, 454f, 0.25f, 0.25f, 0);
	}
}