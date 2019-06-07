package org.java.ecluse;

import com.badlogic.gdx.Gdx;

public class Valve{
	
	Captor captorHigher;
	Captor captorLower;

	boolean isRight;
	boolean isOpen;

	public Valve(Captor captorHigher, Captor captorLower, boolean isRight){
		this.captorHigher = captorHigher;
		this.captorLower = captorLower;
		this.isRight = isRight;
	}

	public void openValve(){
		if (!this.isRight){

			if (this.captorLower.boatNearby || this.captorHigher.inEcluse) {
				this.isOpen = true;
			}else{
				this.isOpen = false;
			}
			
		}else{

			if (this.captorHigher.boatNearby || this.captorLower.inEcluse) {
				this.isOpen = true;
			}else{
				this.isOpen = false;
			}

		}
	}

	public void update(){
		this.openValve();
	}

}