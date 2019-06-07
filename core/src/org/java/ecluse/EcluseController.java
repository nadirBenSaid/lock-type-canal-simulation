package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EcluseController{

	ShapeRenderer shapeRen;

	EcluseWater ecluseWater;
	Captor lowerCaptor;
	Captor higherCaptor;
	Valve rightValve;
	Valve leftValve;
	Gate rightGate;
	Gate leftGate;

	Boat boat;

	// boolean ecluseReady;

	public EcluseController(Boat boat){
		ecluseWater = new EcluseWater();
		shapeRen = new ShapeRenderer();
		lowerCaptor = new Captor(boat, ecluseWater, false);
		higherCaptor = new Captor(boat, ecluseWater, true);
		rightValve = new Valve(higherCaptor, lowerCaptor, true);
		leftValve = new Valve(higherCaptor, lowerCaptor, false);
		rightGate = new Gate(higherCaptor, lowerCaptor, ecluseWater, true);
		leftGate = new Gate(higherCaptor, lowerCaptor, ecluseWater, false);

		this.boat = boat;
	}

	public void controller(){
		if (rightValve.isOpen) {
			ecluseWater.isMovingY = true;
			ecluseWater.goUp = true;
			if (lowerCaptor.inEcluse) {
				boat.isMovingY = true;
				boat.goUp = true;
			}
		}else if (leftValve.isOpen) {
			ecluseWater.isMovingY = true;
			ecluseWater.goUp = false;
			if (higherCaptor.inEcluse) {
				boat.isMovingY = true;
				boat.goUp = false;
			}
		}else{
			boat.isMovingY = false;
			ecluseWater.isMovingY = false;
			ecluseWater.goUp = false;
		}

		if (lowerCaptor.boatNearby || higherCaptor.inEcluse) {
			if (leftGate.isOpen) {
				boat.isMovingX = true;
			}else{
				boat.isMovingX = false;
			}	
		}

		if (higherCaptor.boatNearby || lowerCaptor.inEcluse) {
			if (rightGate.isOpen) {
				boat.isMovingX = true;
			}else{
				boat.isMovingX = false;
			}	
		}

		// if (rightGate.isOpen || leftGate.isOpen) {
		// 	boat.isMovingX = true;
		// }else{
		// 	boat.isMovingX = false;
		// }

	}

	public void render(SpriteBatch batch){
		this.controller();
		ecluseWater.render(batch);
		higherCaptor.update();
		lowerCaptor.update();
		rightValve.update();
		leftValve.update();
		rightGate.update();
		leftGate.update();
	}

	public void shapeRender(){
		shapeRen.begin(ShapeRenderer.ShapeType.Filled);
		shapeRen.setColor(Color.BLACK);
		shapeRen.rect(800-16, 0, 32, Gdx.graphics.getHeight()/2);
		shapeRen.rect(Gdx.graphics.getWidth()-816, 0, 32, Gdx.graphics.getHeight()/2+16);
		shapeRen.end();
	}

}