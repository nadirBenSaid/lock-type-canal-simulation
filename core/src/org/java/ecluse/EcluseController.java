package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class EcluseController{

	ShapeRenderer shapeRen;

	Defect defect;

	EcluseWater ecluseWater;
	Captor lowerCaptor;
	Captor higherCaptor;
	Valve rightValve;
	Valve leftValve;
	Gate rightGate;
	Gate leftGate;

	Boat boat;

	BitmapFont font;

	public EcluseController(Boat boat){

		defect = new Defect();

		ecluseWater = new EcluseWater();
		shapeRen = new ShapeRenderer();
		lowerCaptor = new Captor(boat, ecluseWater, false);
		higherCaptor = new Captor(boat, ecluseWater, true);
		rightGate = new Gate(higherCaptor, lowerCaptor, ecluseWater, true);
		leftGate = new Gate(higherCaptor, lowerCaptor, ecluseWater, false);
		rightValve = new Valve(higherCaptor, lowerCaptor, rightGate, true);
		leftValve = new Valve(higherCaptor, lowerCaptor, rightGate, false);

		this.boat = boat;

		font = new BitmapFont();
		font.setColor(Color.RED);
		font.getData().setScale(3, 3);
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
			if (rightGate.isOpen) {
				boat.isMovingX = true;
			}else{
				boat.isMovingX = false;
			}	
		}

		if (higherCaptor.boatNearby || lowerCaptor.inEcluse) {
			if (leftGate.isOpen) {
				boat.isMovingX = true;
			}else{
				boat.isMovingX = false;
			}	
		}

	}

	public void render(SpriteBatch batch){
		this.controller();
		defect.causeDefect();
		ecluseWater.render(batch);
		if (!defect.captorDefect) {
			higherCaptor.update();
			lowerCaptor.update();
		}else{
			font.draw(batch, "Warning: Captors not working", Gdx.graphics.getWidth()/3 + 50, Gdx.graphics.getHeight()*3/8);
		}
		if (!defect.valveDefect) {
			rightValve.update();
			leftValve.update();
		}else{
			font.draw(batch, "Warning: Valves not working", Gdx.graphics.getWidth()/3 + 50, Gdx.graphics.getHeight()*2/8);
		}
		if (!defect.gateDefect) {
			rightGate.update();
			leftGate.update();
		}else{
			font.draw(batch, "Warning: Gates not working", Gdx.graphics.getWidth()/3 + 50, Gdx.graphics.getHeight()/8);
		}
	}

	public void shapeRender(){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		shapeRen.begin(ShapeRenderer.ShapeType.Filled);
		shapeRen.setColor(Color.BLACK);
		leftValve.render(shapeRen);
		rightValve.render(shapeRen);
		leftGate.render(shapeRen);
		rightGate.render(shapeRen);
		shapeRen.setColor(new Color(1,1,1,0.75f));
		shapeRen.rect(Gdx.graphics.getWidth()/3-16, 0, 32, Gdx.graphics.getHeight()/2-400);
		shapeRen.rect(Gdx.graphics.getWidth()*2/3-16, 0, 32, Gdx.graphics.getHeight()/2-400);
		shapeRen.rect(Gdx.graphics.getWidth()/3-16, Gdx.graphics.getHeight()/2-400+48, 32, 230);
		shapeRen.rect(Gdx.graphics.getWidth()*2/3-16, Gdx.graphics.getHeight()/2-400+48, 32, 320);
		shapeRen.end();
	}

}