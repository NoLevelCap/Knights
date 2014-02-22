package com.nolevelcap.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nolevelcap.fight.Actor;

public class Tile {
	private int x;
	private int y;
	private static final int WIDTH = 64;
	private static final int HEIGHT = 64;
	private Texture tex;
	private boolean occ;
	private Actor iOcc;
	
	public Tile(int row, int y){
		this.x = (row*WIDTH)+16;
		this.y = y;
		this.tex = new Texture(Gdx.files.internal("world/tile.png"));
	}
	
	public void render(SpriteBatch batch){
		batch.draw(tex, x, y, WIDTH, HEIGHT);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y+(HEIGHT-5);
	}

	public boolean isOcc() {
		return occ;
	}
	
	public Actor isOccBy() {
		return iOcc;
	}

	public void setOcc(Actor act) {
		if(act==null){
			this.occ = false;
			this.iOcc = null;
		} else {
			this.occ = true;
			this.iOcc = act;
		}
	}
}
