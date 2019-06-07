package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Valve extends Movable{
	
	Captor captorHigher;
	Captor captorLower;

	boolean isRight;
	boolean isOpen;

	public Valve(Captor captorHigher, Captor captorLower, boolean isRight){
		super();
		this.captorHigher = captorHigher;
		this.captorLower = captorLower;
		this.isRight = isRight;
		if (isRight) {
			this.posX = 800-4;
		}else{
			this.posX = Gdx.graphics.getWidth()-804;
		}
		this.posY = Gdx.graphics.getHeight()/2-400;
		this.isMovingY = true;
		this.speedY = 0.5f;
		this.trajectoryMinY = this.posY-48;
		this.trajectoryMaxY = this.posY;
	}

	public void openValve(){
		if (this.isRight){

			if (this.captorLower.boatNearby || this.captorHigher.inEcluse) {
				this.goUp = false;
			}else{
				this.goUp = true;
			}
			
		}else{

			if (this.captorHigher.boatNearby || this.captorLower.inEcluse) {
				this.goUp = false;
			}else{
				this.goUp = true;
			}

		}

		if (this.posY == this.trajectoryMaxY) {
			this.isOpen = false;
		}else{
			this.isOpen = true;
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
		this.openValve();
	}

	public void render(ShapeRenderer shapeRen){
		shapeRen.rect(this.posX, this.posY, 8, 48);
	}

}