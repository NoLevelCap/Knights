package com.nolevelcap.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.actors.Knight.AnimationSet;

public class Sword {
	private final int Scale = 8;
	private final static int SrcHeight = 12;
	private final static int SrcWidth = 12;
	private final int Height = SrcHeight*Scale;
	private final int Width = SrcWidth*Scale;
	private final int Pixel = Scale;
	private int x, y;
	private boolean fx, fy;
	private int[] shiftX, shiftY;
	private int currentR;
	

	
	public int aIndex;
	
	
	public Knight knight;
	
	public TextureRegion[] skins;
	
	public TextureRegion skin;
	
	public Rectangle pixeltip;
	
	public Sword(Knight knight, int rx, int ry, boolean fx, boolean fy){
		x = rx;
		y = ry;
		this.knight = knight;
		prepareSkins(fx, fy);
		flip(fx, fy);
		setSkin(0);
	}
	
	private void initSkins(int[] ShiftX, int[] ShiftY, TextureRegion[] skins){
		this.shiftX = ShiftX;
		this.shiftY = ShiftY;
		this.skins = skins;
	}
	
	

	
	private void prepareSkins(boolean fx, boolean fy){
		int[] ShiftX;
		if(fx){
			ShiftX = new int[] {(Pixel*0), (Pixel*0), (Pixel*0), (Pixel*7), (Pixel*9)};
		} else {
			ShiftX = new int[] {(Pixel*11), (Pixel*11), (Pixel*11), (Pixel*4), (Pixel*2)};
		}
		int[] ShiftY = new int[] {(Pixel*2), (Pixel*4), (Pixel*11), (Pixel*11), (Pixel*11)};
		TextureRegion[] Skins = new TextureRegion[] 
				{new TextureRegion(new Texture(Gdx.files.internal("knights/sword.png")), 0, 0, SrcWidth, SrcHeight),
				new TextureRegion(new Texture(Gdx.files.internal("knights/sword.png")), SrcWidth, 0, SrcWidth, SrcHeight),
				new TextureRegion(new Texture(Gdx.files.internal("knights/sword.png")), 2*SrcWidth, 0, SrcWidth, SrcHeight),
				new TextureRegion(new Texture(Gdx.files.internal("knights/sword.png")), 3*SrcWidth, 0, SrcWidth, SrcHeight),
				new TextureRegion(new Texture(Gdx.files.internal("knights/sword.png")), 0, SrcHeight, SrcWidth, SrcHeight)};
		initSkins(ShiftX, ShiftY, Skins);
	}
	
	public void render(SpriteBatch batch){
		batch.draw(skin, x, y, Width, Height);
	}
	
	public void debugRender(ShapeRenderer batch){
		batch.setColor(0f, 0.5f, 0.5f, 0.1f);
		batch.rect(pixeltip.x, pixeltip.y, pixeltip.width, pixeltip.height);
	}
	

	public void updateRX(int rx){
		this.x += rx;
		pixeltip.setX(this.x+shiftX[currentR]);
	}

	public void updateRY(int ry){
		this.y += ry;
		pixeltip.setY(this.y+shiftY[currentR]);
	}
	
	public void NoneSprite(){
		setSkin(0);
	}
	
	public void setSkin(int r){
		this.currentR = r;
		if(!(skins[r].isFlipX())){
			skins[r].flip(fx, fy);
		}
		skin = skins[r];
		this.pixeltip = new Rectangle(this.x+shiftX[r], this.y+shiftY[r], Pixel, Pixel);
	}
	
	public void flip(boolean fx, boolean fy){
		this.fx = fx;
		this.fy = fy;
	}

	public void setRX(int rx) {
		this.x = rx;
		pixeltip.setX(shiftX[currentR]);
	}
	

}
