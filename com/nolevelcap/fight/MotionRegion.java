package com.nolevelcap.fight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MotionRegion extends MotionObject {
	
	private TextureRegion tex;
	
	public MotionRegion(Texture tex, int Width, int Height, int RegionX, int RegionY, int ManX, int ManY, int SwordX, int SwordY) {
		this(new TextureRegion(tex, RegionX, RegionY, Width, Height), ManX, ManY, SwordX, SwordY);
	}

	public MotionRegion(TextureRegion tex, int ManX, int ManY, int SwordX, int SwordY) {
		super(ManX, ManY, SwordX, SwordY);
	}

	public TextureRegion getTex() {
		return tex;
	}

	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}

}
