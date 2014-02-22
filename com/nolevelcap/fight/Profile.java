package com.nolevelcap.fight;

import com.badlogic.gdx.graphics.Texture;

public class Profile {
	private Texture tex;
	private int Health;
	private int Stamina;
	
	public Profile(Texture tex,int Health,int Stamina){
		this.setHealth(Health);
		this.setStamina(Stamina);
		this.setTex(tex);
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}

	public int getStamina() {
		return Stamina;
	}

	public void setStamina(int stamina) {
		Stamina = stamina;
	}
}
