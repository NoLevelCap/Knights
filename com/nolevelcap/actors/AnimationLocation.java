package com.nolevelcap.actors;

public enum AnimationLocation {
	NONE(new int[]{0}, //ManX
			new int[]{0}, //SwordX
			new int[]{0}, //ManY
			new int[]{0}, //SwordY
			new int[]{0}),//SwordR
	WALKING_FORWARD(new int[]{0,0,2,1,1,0}, //ManX
					new int[]{0,2,4,-1,-1,0}, //SwordX
					new int[]{0,0,0,0,0,0}, //ManY
					new int[]{0,-2,0,0,2,0}, //SwordY
					new int[]{0,0,0,0,0,0}),//SwordR
	
	WALKING_BACKWARD(new int[]{0,0,-2,-1,-1,0}, //ManX
					new int[]{0,2,-1,-3,-2,0}, //SwordX
					new int[]{0,0,0,0,0,0}, //ManY
					new int[]{0,-2,0,0,2,0}, //SwordY
					new int[]{0,0,0,0,0,0}),//SwordR
	
	JABBING(		new int[]{0,0,0,0,0,0,0,0}, //ManX
					new int[]{2,0,1,1,-1,-1,0,-2}, //SwordX
					new int[]{0,0,0,0,0,0,0,0}, //ManY
					new int[]{0,1,0,0,0,0,-1,0}, //SwordY
					new int[]{0,0,0,0,0,0,0,0}),//SwordR
	
	SLAM(	new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //ManX
			new int[]{2,0,1,2,1,1,0,-1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,-2,-2,0,-2}, //SwordX
			new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //ManY
			new int[]{0,1,0,0,1,1,2,0,3,1,0,0,0,0,-1,-1,0,0,0,0,0,0,0,0,0,0,-1,-1,-2,-1,-1,0,0,-1,0}, //SwordY
			new int[]{0,0,1,1,2,2,3,3,4,4,4,4,4,4,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});//SwordR
	
	int[] ManX, SwordX, ManY, SwordY, SwordR;
	
	AnimationLocation(int[] ManX, int[] SwordX, int[] ManY, int[] SwordY, int[] SwordR){
		this.ManX = ManX;
		this.ManY = ManY;
		this.SwordX = SwordX;
		this.SwordY = SwordY;
		this.SwordR = SwordR;
	}
}
