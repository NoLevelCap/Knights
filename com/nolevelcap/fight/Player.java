package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nolevelcap.actors.Knight.AnimationLoc;
import com.nolevelcap.world.Tile;
import com.nolevelcap.fight.abilities.Ability;
import com.nolevelcap.fight.abilities.BasicAbility;
import com.nolevelcap.fight.abilities.BasicAttack;

public class Player extends Actor{
	private final int Scale = 8;
	private final static int SrcHeight = 24;
	private final static int SrcWidth = 10;
	public final int Height = SrcHeight*Scale;
	public final int Width = SrcWidth*Scale;
	public final int XShift = 0*Scale;
	public final int YShift = 0*Scale;
	

	public Player(Scene scene, Texture tex, int x) {
		super(scene);
		
		
		super.setFlipSettings(false, false);
		super.setTexture(new TextureRegion(tex, 0, 0, SrcWidth, SrcHeight));
		super.setLocation(x, 100);
		super.setAbilities(new Ability[]{
				new BasicAttack(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png"))), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 16, 0, 16, 8), tex, 16, 0, Scale, SrcWidth, SrcHeight, 2),
				new BasicAbility(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png")), 0, 0, 16, 16), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 16, 0, 16, 8), "Ability_2", 16*Scale+16, 0, Scale, 1),
				new BasicAbility(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png")), 0, 0, 16, 16), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 16, 0, 16, 8), "Ability_3", 32*Scale+16, 0, Scale, 1),
				new BasicAbility(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png")), 0, 0, 16, 16), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 16, 0, 16, 8), "Ability_4", 48*Scale+16, 0, Scale, 1),
				new BasicAbility(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png")), 0, 0, 16, 16), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 16, 0, 16, 8), "Ability_5", 64*Scale+16, 0, Scale, 1),
				new BasicAbility(this, new TextureRegion(new Texture(Gdx.files.internal("knights/abilities_signs.png")), 0, 0, 16, 16), new TextureRegion(new Texture(Gdx.files.internal("knights/abilities.png")), 32, 0, 16, 8), "Ability_6", 80*Scale+16, 0, Scale, 1)
		});
		super.setRenderAbilities(true);
		super.setWeapon(new Sword(this, (-1*Scale), (8*Scale), super.getFX(), super.getFY(), new Texture(Gdx.files.internal("knights/sword.png"))));
		//walking = new Animation(this, 
				//tex, //Texture
				//SrcWidth, //Width
				//SrcHeight, //Height
				//6, //Frames
				//75, //Repeat Times
			//	new int[]{1*SrcWidth,2*SrcWidth,3*SrcWidth,4*SrcWidth,5*SrcWidth,6*SrcWidth}, //RegionX
				//new int[]{0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight,0*SrcHeight}, //RegionY
				//new int[]{0*Scale,3*Scale,1*Scale,2*Scale,2*Scale,0*Scale}, //moveX
				//new int[]{0*Scale,0*Scale,0*Scale,0*Scale,0*Scale,0*Scale}, //moveY
				//new int[]{2*Scale,1*Scale, -1*Scale,-1*Scale,-1*Scale,0*Scale}, //moveSwordX
				//new int[]{0*Scale,-1*Scale,1*Scale, 0*Scale, 0*Scale,0*Scale}, //moveSwordY
				//3);
		this.setChosenAbility(getAbilities()[0]);
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch, XShift, YShift, Width, Height);
	}
	
	public void renderUI(SpriteBatch batch, BitmapFont font){
		for(Ability a: getAbilities()){
			if(a.isAbilitySelected()){
				batch.setColor(0.50f, 0.00f, 0.00f, 0.50f);
			} else {
				//Gdx.app.log("A", a.getRect().x+" X; "+a.getRect().y+" Y; "+Ltap.x+" LX; "+Ltap.y+" LY; "+a.getRect().width+" W; "+a.getRect().height+" H; ");
			}
			a.render(batch, font);
			batch.setColor(1.00f, 1.00f, 1.00f, 1f);
		}
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void animation() {
		getAnimation().animation();
	}
	
	public void change(){
		//walking.reset();
		//walking.setRunning(true);
		//super.setTexture(walking.getFrame(walking.getCurrentIndex()));
		//walking.setCurrentIndex(walking.getCurrentIndex()+1);
	}

}
