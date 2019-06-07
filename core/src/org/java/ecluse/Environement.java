package org.java.ecluse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Environement implements Disposable{

	Water water;
	Background background;
	Land land;

	Person boy;
	Girl girl;

	Object stone;
	Object tree1;
	Object tree2;
	Object shroom;
	Object sign;
	Object bush;
	Object root;

	public Environement(){
		background = new Background();
		land = new Land();
		girl = new Girl();
		boy = new Person("boy", 3, 5, 3, 5, 3, 5);
		stone = new Object("stone.png");
		tree1 = new Object("tree1.png");
		tree2 = new Object("tree2.png");
		shroom = new Object("mushroom.png");
		sign = new Object("sign.png");
		bush = new Object("bush.png");
		root = new Object("root.png");
		water = new Water();
	}

	public void render(SpriteBatch batch){
		background.render(batch);
		land.render(batch);
		root.render(batch, Gdx.graphics.getWidth()/2-2*128, Gdx.graphics.getHeight()/2+64);
		stone.render(batch, Gdx.graphics.getWidth()/2+128, Gdx.graphics.getHeight()/2+64);
		boy.render(batch);
		bush.render(batch, 128, Gdx.graphics.getHeight()/2+52);
		// bush.render(batch, Gdx.graphics.getWidth()/2-4.5f*128, Gdx.graphics.getHeight()/2+48);
		bush.render(batch, Gdx.graphics.getWidth()-4.5f*128, Gdx.graphics.getHeight()/2+48);
		tree1.render(batch, Gdx.graphics.getWidth()/2-5.5f*128, Gdx.graphics.getHeight()/2+48);
		tree2.render(batch, Gdx.graphics.getWidth()-4*128, Gdx.graphics.getHeight()/2+40);
		girl.render(batch);
		shroom.render(batch, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+40);
		sign.render(batch, Gdx.graphics.getWidth()/2-3*128, Gdx.graphics.getHeight()/2+36);
		water.render(batch);
	}

	public void dispose(){
		stone.dispose();
		tree1.dispose();
		tree2.dispose();
		shroom.dispose();
		sign.dispose();
		bush.dispose();
		root.dispose();
		background.dispose();
		land.dispose();
		water.dispose();
		girl.dispose();
		boy.dispose();
	}

}