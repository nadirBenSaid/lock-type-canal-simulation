package org.java.ecluse;

public abstract class Movable{

	float speedX = 0;
	float speedY = 0;
	float posX=0;
	float posY=0;

	boolean goRight = true;
	boolean goUp = true;
	boolean isMovingX = true;
	boolean isMovingY = false;
	boolean isTop = false;
	boolean isBottom = true;

	float trajectoryMinX = 0;
	float trajectoryMaxX = 0;
	float trajectoryMinY = 0;
	float trajectoryMaxY = 0;

	public Movable(){
		
	}

	public Movable(float speedX, float speedY, float initialX, float initialY, float minX, float maxX, float minY, float maxY){
		this.speedX = speedX;
		this.speedY = speedY;
		this.posX = initialX;
		this.posY = initialY;
		this.trajectoryMinX = minX;
		this.trajectoryMaxX = maxX;
		this.trajectoryMinY = minY;
		this.trajectoryMaxY = maxY;
	}

	public void moveLeft(){
		if (posX<=trajectoryMinX) {
			return;
		}
		posX-= speedX;
	}
	public void moveRight(){
		if (posX>=trajectoryMaxX) {
			return;
		}
		posX+= speedX;
	}
	public void moveUp(){
		if (posY>=trajectoryMaxY) {
			return;
		}
		posY+= speedY;
	}
	public void moveDown(){
		if (posY<=trajectoryMinY) {
			return;
		}
		posY-= speedY;
	}

	abstract void flagController();
	abstract void posController();
	abstract void stateController();
}