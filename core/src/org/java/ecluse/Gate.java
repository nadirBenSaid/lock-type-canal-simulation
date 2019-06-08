package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Gate extends Movable{
	
	Captor captorHigher;
	Captor captorLower;
	EcluseWater ecluse;

	boolean isLeft;
	boolean isOpen;
	boolean isClose;

	public Gate(Captor captorHigher, Captor captorLower, EcluseWater ecluse, boolean isLeft){
		super();
		this.ecluse = ecluse;
		this.captorHigher = captorHigher;
		this.captorLower = captorLower;
		this.isLeft = isLeft;
		this.posY = Gdx.graphics.getHeight()/2-170+48;
		if (isLeft) {
			this.posX = Gdx.graphics.getWidth()/3-4;
			this.trajectoryMinY = this.posY-140;
		}else{
			this.posX = Gdx.graphics.getWidth()*2/3-4;
			this.trajectoryMinY = this.posY-50;
		}
		this.isMovingY = true;
		this.speedY = 0.5f;
		this.trajectoryMaxY = this.posY;
	}

	public void openGate(){
		if (this.isLeft){

			if ((this.captorLower.boatNearby && this.ecluse.isBottom)||(this.captorHigher.inEcluse && this.ecluse.isBottom)) {
				this.goUp = false;
			}else{
				this.goUp = true;
			}

		}else{

			if ((this.captorHigher.boatNearby && this.ecluse.isTop)||(this.captorLower.inEcluse && this.ecluse.isTop)) {
				this.goUp = false;
			}else{
				this.goUp = true;
			}

		}

		if (this.posY == this.trajectoryMinY) {
			this.isOpen = true;
		}else if(this.posY == this.trajectoryMaxY){
			this.isClose = true;
		}else{
			this.isOpen = false;
			this.isClose = false;
		}
	}

	public void flagController(){

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

	public void update(){
		this.posController();
		this.openGate();
	}

	public void render(ShapeRenderer shapeRen){
		if (!this.isLeft) {
			shapeRen.rect(this.posX, this.posY, 8, 140);
		}else{
			shapeRen.rect(this.posX, this.posY, 8, 140);
		}
	}

}