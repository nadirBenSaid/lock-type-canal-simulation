package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

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
		if (leftValve.isOpen) {
			ecluseWater.isMovingY = true;
			ecluseWater.goUp = true;
			if (lowerCaptor.inEcluse) {
				boat.isMovingY = true;
				boat.goUp = true;
			}
		}else if (rightValve.isOpen) {
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
		Gdx.gl.glEnable(GL20.GL_BLEND);
		shapeRen.begin(ShapeRenderer.ShapeType.Filled);
		shapeRen.setColor(Color.BLACK);
		leftValve.render(shapeRen);
		rightValve.render(shapeRen);
		shapeRen.setColor(new Color(1,1,1,0.5f));
		shapeRen.rect(800-16, 0, 32, Gdx.graphics.getHeight()/2-400);
		shapeRen.rect(Gdx.graphics.getWidth()-816, 0, 32, Gdx.graphics.getHeight()/2-400);
		shapeRen.rect(800-16, Gdx.graphics.getHeight()/2-400+48, 32, 230);
		shapeRen.rect(Gdx.graphics.getWidth()-816, Gdx.graphics.getHeight()/2-400+48, 32, 320);
		shapeRen.end();
	}

}