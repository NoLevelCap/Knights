package com.nolevelcap.fight;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nolevelcap.utils.Timer;

public class Animation {
	private Texture tex;
	private TextureRegion upload;
	private int[] regionX;
	private int[] regionY;
	private int[] manPositionX;
	private int[] manPositionY;
	private int[] weaponX;
	private int[] weaponY;
	private int Width;
	private int Height;
	private int CurrentIndex;
	private int Frames;
	private TextureRegion[] frames;
	private Timer timer;
	private boolean running;
	private Actor handler;
	private int repeatTimes;
	private int repeatedTime;
	private boolean finished;
	
	public Animation(Actor handler){
		this(	handler, 
				new Texture(Gdx.files.internal("knights/knight.png")), //Texture
				handler.getTexture().getRegionWidth(), //Width
				handler.getTexture().getRegionHeight(), //Height
				1, //Frames
				0, //Repeat Times
			    new int[]{0}, //RegionX
				new int[]{0}, //RegionY
				new int[]{0}, //moveX
				new int[]{0}, //moveY
				new int[]{0}, //moveSwordX
				new int[]{0}, //moveSwordY
				3);
	}
	
	public Animation(Actor handler, MotionRegionCollection mrc, int Timing, int RepeatTimes){
		this(handler, 
			mrc.getTex(),
			mrc.getWidth(),
			mrc.getHeight(),
			mrc.getFrames(),
			Timing,
			mrc.getRegionX(),
			mrc.getRegionY(), 
			mrc.getManX(),
			mrc.getManY(),
			mrc.getSwordX(),
			mrc.getSwordY(),
			RepeatTimes);
	}
	
	public Animation(Actor handler, Texture tex, int Width, int Height, int Frames, int Timing){
		this(handler, tex, Width,Height, Frames, Timing, 1);
	}
	
	public Animation(Actor handler, Texture tex, int Width, int Height, int Frames, int Timing, int repeatTimes){
		this(handler, tex, Width,Height, Frames, Timing, new int[Frames], new int[Frames], repeatTimes);
	}
	
	public Animation(Actor handler, Texture tex, int Width, int Height, int Frames, int Timing, int[] regionX, int[] regionY, int repeatTimes){
		this(handler, tex, Width,Height, Frames, Timing, regionX, regionY, new int[Frames], new int[Frames], new int[Frames], new int[Frames], repeatTimes);
	}
	
	public Animation(Actor handler, Texture tex, int Width, int Height, int Frames, int Timing, int[] regionX, int[] regionY, int[] manPositionX, int[] manPositionY, int[] swordX, int[] swordY, int repeatTimes){
		this.handler = handler;
		this.setTimer(new Timer(Timing));
		this.setFrames(Frames);
		this.setHeight(Height);
		this.setWidth(Width);
		this.setTex(tex);
		this.setRegionX(regionX);
		this.setRegionY(regionY);
		this.setManPositionX(manPositionX);
		this.setManPositionY(manPositionY);
		this.setWeaponX(swordX);
		this.setWeaponY(swordY);
		this.setRunning(false);
		configureTexFrames();
		this.setRepeatTimes(repeatTimes);
		this.setRepeatedTime(0);
		this.setCurrentIndex(0);
	}
	
	public Animation(Actor handler, int Timing, int[] manPositionX, int[] manPositionY, int[] swordX, int[] swordY, int repeatTimes, TextureRegion... texRegions){
		this.handler = handler;
		this.setTimer(new Timer(Timing));
		this.setTexFrames(texRegions);
		
		this.setFrames(texRegions.length);
		if(getFrames()>0){
		this.setHeight(getFrame(0).getRegionHeight());
		this.setWidth(getFrame(0).getRegionWidth());
		this.setTex(getFrame(0).getTexture());
		int[] regionX = new int[getFrames()];
		int[] regionY = new int[getFrames()];
		int ind = 0;
		for(TextureRegion r: getTexFrames()){
			regionX[ind] = r.getRegionX();
			regionY[ind] = r.getRegionY();
			ind++;
		}
		this.setVRegionX(regionX);
		this.setVRegionY(regionY);
		}
		
		this.setManPositionX(manPositionX);
		this.setManPositionY(manPositionY);
		this.setWeaponX(swordX);
		this.setWeaponY(swordY);
		this.setRunning(false);
		this.setRepeatTimes(repeatTimes);
		this.setRepeatedTime(0);
		this.setCurrentIndex(0);
	}
	
	public void animation(){
		//Gdx.app.log("Running", "is "+running+" "+(getCurrentIndex()+1)+" "+getFrames());
		if(getTimer().nextFrame()&&running){
			if(getCurrentIndex()+1==getFrames()){
				upload();
				setRepeatedTime(getRepeatedTime()+1);
				if(getRepeatedTime()==getRepeatTimes()){
					Gdx.app.log("Finishing", getRepeatedTime()+"/"+getRepeatTimes());
					setRunning(false);
					setFinished(true);
					setRepeatedTime(0);
				} else {
					setFinished(false);
					Gdx.app.log("Restarting", getRepeatedTime()+"/"+getRepeatTimes());
					setCurrentIndex(0);
				}
			} else {
				upload();
				setCurrentIndex(getCurrentIndex()+1);
				
			}
		}
	}
	
	public void upload(){
		Gdx.app.log("A2", getUpload()+" ");
		getHandler().setTexture(getUpload());
		getHandler().getWeapon().increaseX(this.getWeaponX(getCurrentIndex()));
		getHandler().getWeapon().increaseY(this.getWeaponY(getCurrentIndex()));
		getHandler().updateCurrentLoc(this.getManPositionX(getCurrentIndex()), this.getManPositionY(getCurrentIndex()));
	}
	
	public TextureRegion getFrame(int i){
		if(i<getFrames()||i>=0){
			return frames[i];
		} else {
			return frames[getCurrentIndex()];
		}
	}
	
	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public int[] getRegionX() {
		return regionX;
	}
	
	public int getRegionX(int i) {
		return regionX[i];
	}

	public void setRegionX(int[] regionX) {
		this.regionX = regionX;
	}
	
	public void setVRegionX(int... regionX) {
		this.regionX = regionX;
	}
	
	public void setVRegionY(int... regionY) {
		this.regionY = regionY;
	}

	public int[] getRegionY() {
		return regionY;
	}
	
	public int getRegionY(int i) {
		return regionY[i];
	}

	public void setRegionY(int[] regionY) {
		this.regionY = regionY;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getCurrentIndex() {
		return CurrentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		//Gdx.app.log("A", "ABC"+currentIndex+"A"+getFrames());
		if(currentIndex<getFrames()&&currentIndex>=0){
			CurrentIndex = currentIndex;
			setUpload(getFrame(currentIndex));
		} else {
			CurrentIndex = getFrames()-1;
		}
	}

	public int getFrames() {
		return Frames;
	}

	public void setFrames(int frames) {
		Frames = frames;
	}



	public TextureRegion[] getTexFrames() {
		return frames;
	}



	public void setTexFrames(TextureRegion[] frames) {
		this.frames = frames;
	}
	
	public void configureTexFrames() {
		this.frames = new TextureRegion[this.getFrames()];
		for(int i = 0; i<this.getFrames(); i++){
			this.frames[i] = new TextureRegion(getTex(), getRegionX(i), getRegionY(i), getWidth(), getHeight());
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Actor getHandler() {
		return handler;
	}

	public TextureRegion getUpload() {
		return upload;
	}

	public void setUpload(TextureRegion upload) {
		this.upload = upload;
	}
	
	public void reset(){
		setRunning(false);
		setFinished(false);
		setCurrentIndex(0);
	}

	public int getRepeatedTime() {
		return repeatedTime;
	}

	public void setRepeatedTime(int repeatedTime) {
		this.repeatedTime = repeatedTime;
	}

	public int getRepeatTimes() {
		return repeatTimes;
	}

	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public int[] getManPositionX() {
		return manPositionX;
	}
	
	public int getManPositionX(int i) {
		return manPositionX[i];
	}
	
	public int getManPositionY(int i) {
		return manPositionY[i];
	}

	public void setManPositionX(int[] manPositionX) {
		this.manPositionX = manPositionX;
	}

	public int[] getManPositionY() {
		return manPositionY;
	}

	public void setManPositionY(int[] manPositionY) {
		this.manPositionY = manPositionY;
	}

	public int[] getWeaponX() {
		return weaponX;
	}
	
	public int getWeaponX(int x) {
		return weaponX[x];
	}
	
	public int getWeaponY(int y) {
		return weaponY[y];
	}

	public void setWeaponX(int[] weaponX) {
		this.weaponX = weaponX;
	}

	public int[] getWeaponY() {
		return weaponY;
	}

	public void setWeaponY(int[] weaponY) {
		this.weaponY = weaponY;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
