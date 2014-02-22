package com.nolevelcap.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.fight.FightScene;

public class FightControls implements GestureListener {
	
	FightScene ctx;
	
	long startTime;
	long endTime;
	long timeDiff;
	
	public FightControls(FightScene ctx){
		this.ctx = ctx;
	}
	
	

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		startTime = System.currentTimeMillis();
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		endTime = System.currentTimeMillis();
		timeDiff = endTime - startTime;
		if(timeDiff>300){
			ctx.longTap();
			Gdx.app.log("Time", timeDiff+" MS");
		} else {
		if(count>1){
			Gdx.app.log("TAP", count+" C");
		} else if(count==1){
			ctx.tap(x, y);
		} else {
			//Gdx.app.log("TAP", count+" C");
		}
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		endTime = System.currentTimeMillis();
		timeDiff = endTime - startTime;
		ctx.longTap();
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if(velocityX<-500){
			ctx.swipedLeft();
		} else if(velocityX>500){
			ctx.swipedRight();
		} else {
			Gdx.app.log("VELX", velocityX+" X");
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
