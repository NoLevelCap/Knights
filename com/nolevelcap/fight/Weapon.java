package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Weapon {
	
	private int x, y;
	private Actor holder;
	private TextureRegion tex;
	private boolean flipX, flipY;
	
	public Weapon(){
		
	}
	
	public Weapon(Actor holder, int x, int y, boolean fx, boolean fy){
		this.holder = holder;
		this.x = x;
		this.y = y;
		this.flipX = fx;
		this.flipY = fy;
		this.setTexture(new TextureRegion());
		this.setFlipSettings(flipX, flipY);
	}
	
	public abstract void render(SpriteBatch batch, int shiftX, int shiftY);
	public abstract void logic();
	
	private void flip(TextureRegion tex, boolean flippedX, boolean flippedY){
		boolean toFlipX, toFlipY;
		Gdx.app.log("A", tex+"");
		if(!tex.isFlipX()&&flippedX){
			toFlipX = true;
		} else if(tex.isFlipX()&&!flippedX){
			toFlipX = true;
		} else {
			toFlipX = false;
		}
		
		if(!tex.isFlipY()&&flippedY){
			toFlipY = true;
		} else if(tex.isFlipY()&&!flippedY){
			toFlipY = true;
		} else {
			toFlipY = false;
		}
		
		tex.flip(toFlipX, toFlipY);
	}
	
	public TextureRegion getTexture() {
		return tex;
	}

	public void setTexture(TextureRegion tex) {
		flip(tex, flipX, flipY);
		this.tex = tex;
	}
	
	public void setTexture(TextureRegion tex, boolean flipX, boolean flipY) {
		flip(tex, flipX, flipY);
		this.tex = tex;
	}
	
	public void setFlipSettings(boolean flipX, boolean flipY){
		this.flipX = flipX;
		this.flipY = flipY;
	}

	public Actor getHolder() {
		return holder;
	}

	public void setHolder(Actor holder) {
		this.holder = holder;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void increaseY(int y) {
		this.y += y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void increaseX(int x) {
		this.x += x;
	}

}
