package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.actors.Knight.AnimationLoc;
import com.nolevelcap.world.Tile;

public class Enemy extends Actor{
	private final int Scale = 8;
	private final static int SrcHeight = 24;
	private final static int SrcWidth = 10;
	public final int Height = SrcHeight*Scale;
	public final int Width = SrcWidth*Scale;
	private final int XShift = 0*Scale;
	private final int YShift = 0*Scale;
	
	private Rectangle bounds;
	public Animation walking;
	private boolean ValidToSelect;
	

	public Enemy(Scene scene, Texture tex, int x) {
		super(scene);
		super.setFlipSettings(true, false);
		super.setTexture(new TextureRegion(tex, 0, 0, SrcWidth, SrcHeight));
		super.setLocation(x, 100);
		super.setWeapon(new Sword(this, (-1*Scale), (8*Scale), super.getFX(), super.getFY(), new Texture(Gdx.files.internal("knights/sword.png"))));
		this.setBounds(new Rectangle(super.getCurrentLoc().x, super.getCurrentLoc().y, Width, Height));
		walking = new Animation(this, 
				tex, //Texture
				SrcWidth, //Width
				SrcHeight, //Height
				6, //Frames
				75, //Repeat Times
				new int[]{1*SrcWidth,2*SrcWidth,3*SrcWidth,4*SrcWidth,5*SrcWidth,6*SrcWidth}, //RegionX
				new int[]{0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight}, //RegionY
				new int[]{0*Scale,-3*Scale,-1*Scale,-2*Scale,-2*Scale,0*Scale}, //moveX
				new int[]{0*Scale,0*Scale,0*Scale,0*Scale,0*Scale,0*Scale}, //moveY
				new int[]{-2*Scale,-1*Scale, 1*Scale,1*Scale,1*Scale,0*Scale}, //moveSwordX
				new int[]{0*Scale,-1*Scale,1*Scale, 0*Scale, 0*Scale,0*Scale}, //moveSwordY
				3);
	}

	public void render(SpriteBatch batch) {
		super.render(batch, XShift, YShift, Width, Height);
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void animation() {
		walking.animation();
	}
	
	public void change(){
		walking.reset();
		walking.setRunning(true);
		//super.setTexture(walking.getFrame(walking.getCurrentIndex()));
		//walking.setCurrentIndex(walking.getCurrentIndex()+1);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	
	
	

}
