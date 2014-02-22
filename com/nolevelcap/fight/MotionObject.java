package com.nolevelcap.fight;

public class MotionObject {
	
	private int ManX, ManY, SwordX, SwordY;
	
	public MotionObject(int ManX, int ManY, int SwordX, int SwordY){
		this.setManX(ManX);
		this.setManY(ManY);
		this.setSwordX(SwordX);
		this.setSwordY(SwordY);
	}

	public int getManX() {
		return ManX;
	}

	public void setManX(int manX) {
		ManX = manX;
	}

	public int getManY() {
		return ManY;
	}

	public void setManY(int manY) {
		ManY = manY;
	}

	public int getSwordX() {
		return SwordX;
	}

	public void setSwordX(int swordX) {
		SwordX = swordX;
	}

	public int getSwordY() {
		return SwordY;
	}

	public void setSwordY(int swordY) {
		SwordY = swordY;
	}
}
