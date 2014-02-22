package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;

public class MotionCollection {
	private MotionObject[] motionObject;
	
	public MotionCollection(int[] ManX, int[] ManY, int[] SwordX, int[] SwordY){
		if(ManX.length == ManY.length && SwordX.length == SwordY.length && ManX.length == SwordX.length && ManY.length == SwordY.length){
			motionObject = new MotionObject[ManX.length];
			for(int i = 0; i<ManX.length; i++){
				motionObject[i] = new MotionObject(ManX[i], ManY[i], SwordX[i], SwordY[i]);
			}
		} else {
			Gdx.app.log("MotionCollection", "Error values not equal");
			Gdx.app.exit();
		}
	}
}
