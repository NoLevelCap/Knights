package com.nolevelcap.actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.fight.FightScene;
import com.nolevelcap.utils.Timer;

public class Knight {
	
	//TODO 1 to 8 movement animations
	
	//TODO Death Animation
	
	//TODO Knockout Animation
	
	//TODO Tidy up code
	
	//TODO Create an actor class
	
	//TODO Cannot go past the person
	
	//TODO Jabbing Knocks back
	
	//TODO Slamming Causes the person to crouch
	
	//TODO Crouching
	
	private final int Scale = 8;
	private final static int SrcHeight = 24;
	private final static int SrcWidth = 16;
	private final int Height = SrcHeight*Scale;
	private final int Width = SrcWidth*Scale;
	private final int Pixel = Scale;
	private int x, y;
	public int tile;
	private boolean fx, fy;
	private Timer clock;
	boolean ai;
	
	private int Health;
	
	
	public enum AnimationLoc{
		NONE(new int[]{0}, //ManX
				new int[]{0}, //SwordX
				new int[]{0}, //ManY
				new int[]{0}, //SwordY
				new int[]{0}),//SwordR
		WALKING_FORWARD(new int[]{0, 0,2, 1, 1, 0,2, 1, 1,0}, //ManX
						new int[]{0, 2,4,-1,-1, 2,4,-1,-1,0}, //SwordX
						new int[]{0, 0,0, 0, 0, 0,0, 0, 0,0}, //ManY
						new int[]{0,-2,0, 0, 2,-2,0, 0, 2,0}, //SwordY
						new int[]{0, 0,0, 0, 0, 0, 0,0, 0, 0,0}),//SwordR
		
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
		
		AnimationLoc(int[] ManX, int[] SwordX, int[] ManY, int[] SwordY, int[] SwordR){
			this.ManX = ManX;
			this.ManY = ManY;
			this.SwordX = SwordX;
			this.SwordY = SwordY;
			this.SwordR = SwordR;
		}
	}
	
	public enum AnimationSet{
		None(75, AnimationLoc.NONE, new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 0, SrcWidth, SrcHeight)),
		
		/*WALKING_FORWARD(75, AnimationLoc.WALKING_FORWARD, new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 4*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 5*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 4*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 5*SrcHeight, SrcWidth, SrcHeight)),
							 
		WALKING_BACKWARD(75, AnimationLoc.WALKING_BACKWARD, new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 6*SrcHeight, SrcWidth, SrcHeight), 
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 6*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 6*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 6*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 7*SrcHeight, SrcWidth, SrcHeight)),*/
								 
				JABBING(75/2, AnimationLoc.JABBING, new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 8*SrcHeight, SrcWidth, SrcHeight), 
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 8*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 8*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 8*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 8*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 8*SrcHeight, SrcWidth, SrcHeight),
								 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 8*SrcHeight, SrcWidth, SrcHeight)),
								 
					SLAM(75, AnimationLoc.SLAM, new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 9*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 10*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 11*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 11*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), SrcWidth, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 10*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 3*SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight), 
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 2*SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 1*SrcWidth, 9*SrcHeight, SrcWidth, SrcHeight),
							 new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 9*SrcHeight, SrcWidth, SrcHeight));
		
		
		public TextureRegion[] textures;
		public long timing;
		public AnimationLoc loc;
		
		AnimationSet(long milliseconds, AnimationLoc loc, TextureRegion... tex){
			this.timing = milliseconds;
			this.textures = tex;
			this.loc = loc;
		}
	}
	
	public AnimationSet state = AnimationSet.None;
	public boolean animationNeeded;
	public boolean animationFinished;
	public boolean animationPlaying;
	
	public int aIndex;
	
	public Rectangle head;
	public Rectangle body;
	
	public FightScene scene;
	public Sword sword;
	public String name;
	
	public TextureRegion skin;
	
	public Knight(FightScene scene, boolean fx, boolean fy, int tile, String name){
		this.name = name;
		this.tile = tile;
		this.x = ((tile*64)-(Pixel*4))+16;
		this.y = 24*4;
		this.Health = 100;
		this.scene = scene;
		this.clock = new Timer();
		animationNeeded = false;
		TextureRegion F = new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 0, SrcWidth, SrcHeight);
		setOrientation(fx, fy);
		F.flip(fx, fy);
		changeSkin(F);
		head = new Rectangle(x+(Pixel*6), y+(Pixel*19), 4*Pixel, 5*Pixel);
		body = new Rectangle(x+(Pixel*5), y+(Pixel*8), 6*Pixel, 11*Pixel);
		sword = new Sword(this, x+(Pixel*2), y+(Pixel*8), fx, fy);
	}
	
	public void render(SpriteBatch batch){
		skin = configureOrientation(skin, fx, fy);
		batch.draw(skin, x, y, Width, Height);
		sword.render(batch);
	}
	
	public void debugRender(ShapeRenderer batch){
		batch.setColor(0.0f,0.0f,0.5f,0.1f);
		batch.rect(head.x, head.y, head.width, head.height);
		batch.setColor(0.0f,0.5f,0.0f,0.1f);
		batch.rect(body.x, body.y, body.width, body.height);
		sword.debugRender(batch);
	}
	
	public void walkBackwards(){
		if(!animationPlaying){
			//state = //AnimationSet.WALKING_BACKWARD;
			updateTile(-1);
		}
	}
	
	public void walkForwards(){
		if(!animationPlaying){
			//state = //AnimationSet.WALKING_FORWARD;
			updateTile(1);
		}
	}
	
	public void Jab(){
		if(!animationPlaying){
			state = AnimationSet.JABBING;
		}
	}
	
	public void Slam(){
		if(!animationPlaying){
			state = AnimationSet.SLAM;
		}
	}
	
	
	public void checkForAnimations(){
		if(state!=AnimationSet.None){
			
			animationNeeded = true;
		}
	}
	
	public void Animate(){
		if(animationNeeded){
			Animation(state);
		if(animationFinished){
			Gdx.app.log("AnimationFinishing", "AnimationFinishing");
			animationNeeded = false;
			animationFinished = false;
			animationPlaying = false;
			state = AnimationSet.None;
		}
		}
	}
	
	public void Animation(AnimationSet Anima){
		if(clock.nextFrame()){
			//Gdx.app.log("INFO", this+" "+state.name()+" FX "+fx);
			if(fx){
				updateX(-Anima.loc.ManX[aIndex], -Anima.loc.SwordX[aIndex]);
			} else {
				updateX(Anima.loc.ManX[aIndex], Anima.loc.SwordX[aIndex]);
			}
			updateY(Anima.loc.ManY[aIndex], Anima.loc.SwordY[aIndex]);
			changeSwordRotation(Anima.loc.SwordR[aIndex]);
			animationPlaying = true;
			if((aIndex)<Anima.textures.length){
				changeSkin(Anima.textures[(aIndex)]);
				aIndex++;
			} else {
				NoneSprite();
				aIndex = 0;
				animationFinished = true;
			}
		}
	}
	
	
	
	
	
	
	
	
	public void NoneSprite(){
		changeSkin(new TextureRegion(new Texture(Gdx.files.internal("knights/skins.png")), 0, 0, SrcWidth, SrcHeight));
	}
	
	
	
	public void updateX(int newx, int swordx){
		x+=(Pixel*newx);
		updateBox(newx, 0);
		sword.updateRX(Pixel*swordx);
	}
	
	public void setX(int newx, int swordx){
		x=(Pixel*newx);
		setBox(newx, 0);
		sword.setRX(Pixel*swordx);
	}
	
	public void updateY(int newy, int swordy){
		y+=(Pixel*newy);
		sword.updateRY(Pixel*swordy);
	}
	
	public void updateBox(int newx, int newy){
		head.set(head.x+(Pixel*newx), head.y+(Pixel*newy), head.width, head.height);
		body.set(body.x+(Pixel*newx), body.y+(Pixel*newy), body.width, body.height);
	}
	
	public void setBox(int newx, int newy){
		head.set((Pixel*newx), (Pixel*newy), head.width, head.height);
		body.set((Pixel*newx), (Pixel*newy), body.width, body.height);
	}
	
	public void changeSwordRotation(int r){
		sword.setSkin(r);
	}
	
	public void setOrientation(boolean fx, boolean fy){
		this.fx = fx;
		this.fy = fy;
	}
	
	public TextureRegion configureOrientation(TextureRegion region, boolean fx, boolean fy){
		if(!(region.isFlipX()) && fx){
			region.flip(true, fy);
		} else if(region.isFlipX() && !fx){
			region.flip(true, fy);	
		}
		this.fx = fx;
		this.fy = fy;
		return region;
	}
	
	public void changeSkin(TextureRegion region){
		skin = region;
	}
	
	public void setAiControlled(boolean ai){
		
	}
	
	public int getHealth(){
		return this.Health;
	}
	
	public void upHealth(int m){
		Health += m;
	}
	
	public void updateTile(int increase){
		tile+=increase;
		//setX(scene.tiles[tile]+1, 0);
	}
	
	public void setTile(int set){
		tile=set;
		setX((set*Pixel)-16, 0);
	}
	
	
	
	
}
