package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Defect{

	boolean captorDefect;
	boolean gateDefect;
	boolean valveDefect;

	public void causeDefect(){
		captorDefect = Gdx.input.isKeyPressed(Keys.C);
		gateDefect = Gdx.input.isKeyPressed(Keys.G);
		valveDefect = Gdx.input.isKeyPressed(Keys.V);
	}

}