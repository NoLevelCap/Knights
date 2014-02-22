package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MotionRegionCollection {
	private MotionRegion[] motionObject;
	private Texture tex;
	private int width, height, scale, frames;
	private int[] RegionX, RegionY, ManX, ManY, SwordX, SwordY;
	
	public MotionRegionCollection(Texture tex, int Width, int Height, int[] RegionX, int[] RegionY, int[] ManX, int[] ManY, int[] SwordX, int[] SwordY){
		this(tex, Width, Height, RegionX, RegionY, ManX, ManY, SwordX, SwordY, 8);
	}
	
	public MotionRegionCollection(Texture tex, int Width, int Height, int[] RegionX, int[] RegionY, int[] ManX, int[] ManY, int[] SwordX, int[] SwordY, int Scale){
		if(RegionX.length == RegionY.length && ManX.length == RegionX.length && ManX.length == ManY.length && SwordX.length == SwordY.length && ManX.length == SwordX.length && ManY.length == SwordY.length){
			this.setFrames(ManX.length);
			motionObject = new MotionRegion[getFrames()];
			this.setTex(tex);
			this.setWidth(Width);
			this.setHeight(Height);
			for(int i = 0; i < getFrames(); i++){
				RegionX[i]*=Width;
			}
			this.setRegionX(RegionX);
			for(int i = 0; i < getFrames(); i++){
				RegionY[i]*=Width;
			}
			this.setRegionY(RegionY);
			for(int i = 0; i < getFrames(); i++){
				ManX[i]*=Scale;
			}
			this.setManX(ManX);
			for(int i = 0; i < getFrames(); i++){
				ManY[i]*=Scale;
			}
			this.setManY(ManY);
			for(int i = 0; i < getFrames(); i++){
				SwordX[i]*=Scale;
			}
			this.setSwordX(SwordX);
			for(int i = 0; i < getFrames(); i++){
				SwordY[i]*=Scale;
			}
			this.setSwordY(SwordY);
			this.setScale(Scale);
			
			for(int i = 0; i<ManX.length; i++){
				motionObject[i] = new MotionRegion(tex, Width, Height, RegionX[i]*Width, RegionY[i]*Height, ManX[i]*Scale, ManY[i]*Scale, SwordX[i]*Scale, SwordY[i]*Scale);
			}
		} else {
			Gdx.app.log("MotionRegionCollection", "Error values not equal");
			Gdx.app.exit();
		}
	}
	
	public MotionRegion[] getMotionArray(){
		return motionObject;
	}
	
	public MotionRegion getMotionArray(int i){
		return motionObject[i];
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getRegionX() {
		return RegionX;
	}

	public void setRegionX(int[] regionX) {
		RegionX = regionX;
	}

	public int[] getRegionY() {
		return RegionY;
	}

	public void setRegionY(int[] regionY) {
		RegionY = regionY;
	}

	public int[] getManX() {
		return ManX;
	}

	public void setManX(int[] manX) {
		ManX = manX;
	}

	public int[] getManY() {
		return ManY;
	}

	public void setManY(int[] manY) {
		ManY = manY;
	}

	public int[] getSwordX() {
		return SwordX;
	}

	public void setSwordX(int[] swordX) {
		SwordX = swordX;
	}

	public int[] getSwordY() {
		return SwordY;
	}

	public void setSwordY(int[] swordY) {
		SwordY = swordY;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}
}
