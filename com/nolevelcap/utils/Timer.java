package com.nolevelcap.utils;

import com.badlogic.gdx.Gdx;

public class Timer {
	private long currentTimeStamp;
	private long seconds;
	private long lastTimeStamp;
	private long lastAniTimeStamp;
	private int currentTick;
	private int lastTickStamp;
	private long timing;
	
	public Timer(){
		this.setTiming(0);
		timeStamp();
	}
	
	public Timer(long timing){
		this.setTiming(timing);
		timeStamp();
	}
	
	public boolean nextTick(){
		long TimeDiff = getSystemTime() - lastTimeStamp;
		if(TimeDiff>50){
			timeStamp();
			currentTick++;
			checkSeconds();
			return true;
		} else {
			return false;
		}
	}
	
	public int getTick(){
		return currentTick;
	}
	
	public long getSystemTime(){
		currentTimeStamp = System.currentTimeMillis();
		return currentTimeStamp;
		
	}
	
	public void checkSeconds(){
		long TickDiff = getTick() - lastTimeStamp;
		if(TickDiff == 20){
			seconds++;
			lastTickStamp = getTick();
			Gdx.app.log("Seconds", "S: "+seconds);
		} else {
		}
	}
	
	public void timeStamp(){
		lastTimeStamp = getSystemTime();
	}
	
	public void aniTimeStamp(){
		lastAniTimeStamp = getSystemTime();
	}
	
	public long getAniTimeStamp(){
		return lastAniTimeStamp;
	}
	
	public boolean nextFrame(){
		long TimeDiff = getSystemTime() - getAniTimeStamp();
		if(TimeDiff>timing){
			aniTimeStamp();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean nextFrame(int Customtiming){
		long TimeDiff = getSystemTime() - getAniTimeStamp();
	if(TimeDiff>Customtiming){
		aniTimeStamp();
		return true;
	} else {
		return false;
	}
	}
	
	public boolean timePassed(int timeLength){
		long TimeDiff = getSystemTime() - getAniTimeStamp();
		if(TimeDiff>timeLength){
			//aniTimeStamp();
			return true;
		} else {
			return false;
		}
	}

	public long getTiming() {
		return timing;
	}

	public void setTiming(long timing) {
		this.timing = timing;
	}
}
