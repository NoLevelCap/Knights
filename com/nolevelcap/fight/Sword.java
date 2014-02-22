package com.nolevelcap.fight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sword extends Weapon{
	private final int Scale = 8;
	private final static int SrcHeight = 12;
	private final static int SrcWidth = 12;
	private final int Height = SrcHeight*Scale;
	private final int Width = SrcWidth*Scale;

	public Sword(Actor holder, int x, int y, boolean fx, boolean fy, Texture tex) {
		super(holder, x, y, fx, fy);
		super.setTexture(new TextureRegion(tex, 0, 0, SrcWidth, SrcHeight));
	}

	@Override
	public void render(SpriteBatch batch, int shiftX, int shiftY) {
		batch.draw(super.getTexture(), super.getHolder().getCurrentLoc().x+super.getX()+shiftX, super.getHolder().getCurrentLoc().y+super.getY()+shiftY, Width, Height);
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
	}
	
}
