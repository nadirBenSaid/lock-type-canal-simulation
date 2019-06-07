package org.java.ecluse;

import com.badlogic.gdx.Gdx;

public class Captor{

	Boat boat;
	EcluseWater ecluse;
	
	boolean isUpper;	// flag determines wether captor of low or high level

	boolean boatNearby;
	boolean inEcluse;

	public Captor(Boat boat, EcluseWater ecluse, boolean isUpper){
		this.boat = boat;
		this.ecluse = ecluse;
		this.isUpper = isUpper;
	}

	public void setBoatNearby(){
		if (!this.isUpper){

			if (this.boat.posX > Gdx.graphics.getWidth()/3-360 && this.boat.posX < Gdx.graphics.getWidth()/3 && this.boat.goRight) {
				this.boatNearby = true;
			}else{
				this.boatNearby = false;
			}

			if (this.boat.posX > Gdx.graphics.getWidth()/3 && this.boat.posX < Gdx.graphics.getWidth()/3*2 && this.boat.goRight) {
				this.inEcluse = true;
			}else{
				this.inEcluse = false;
			}

		}else{

			if (this.boat.posX < Gdx.graphics.getWidth()/3*2+10 && this.boat.posX > Gdx.graphics.getWidth()/3*2-350 && !this.boat.goRight) {
				this.boatNearby = true;
			}else{
				this.boatNearby = false;
			}

			if (this.boat.posX > Gdx.graphics.getWidth()/3-350 && this.boat.posX < Gdx.graphics.getWidth()/3*2-350 && !this.boat.goRight) {
				this.inEcluse = true;
			}else{
				this.inEcluse = false;
			}

		}
	}

	public void update(){
		this.setBoatNearby();
	}

}