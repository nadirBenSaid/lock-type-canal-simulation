package org.java.ecluse;

public class Gate{
	
	Captor captorHigher;
	Captor captorLower;
	EcluseWater ecluse;

	boolean isRight;
	boolean isOpen;

	public Gate(Captor captorHigher, Captor captorLower, EcluseWater ecluse, boolean isRight){
		this.ecluse = ecluse;
		this.captorHigher = captorHigher;
		this.captorLower = captorLower;
		this.isRight = isRight;
	}

	public void openGate(){
		if (!this.isRight){

			if ((this.captorLower.boatNearby && this.ecluse.isBottom)||(this.captorHigher.inEcluse && this.ecluse.isBottom)) {
				this.isOpen = true;
			}else{
				this.isOpen = false;
			}

		}else{

			if ((this.captorHigher.boatNearby && this.ecluse.isTop)||(this.captorLower.inEcluse && this.ecluse.isTop)) {
				this.isOpen = true;
			}else{
				this.isOpen = false;
			}

		}
	}

	public void update(){
		this.openGate();
	}

}