package com.nolevelcap.fight.abilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.fight.Actor;
import com.nolevelcap.fight.Animation;

public class BasicAbility extends Ability{
	
	private int SrcWidth;
	private int SrcHeight;
	private int Scale;
	private String name;
	private Animation[] animations;
	private int index;
	
	public BasicAbility(Actor handler, TextureRegion symbol, FileHandle tex, String name, int SrcWidth, int SrcHeight, int Scale, int RegionX, int RegionY, int x, int y, Animation... animations) {
		this(handler, symbol, new Texture(tex), name, SrcWidth, SrcHeight, Scale, RegionX, RegionY, x, y, animations);
	}
	
	public BasicAbility(Actor handler, TextureRegion symbol, Texture tex, String name,  int SrcWidth, int SrcHeight, int Scale, int RegionX, int RegionY, int x, int y, Animation... animations) {
		this(handler, symbol, new TextureRegion(tex, RegionX, RegionY, SrcWidth, SrcHeight), name, x, y, Scale, animations);
		
	}
	
	public BasicAbility(Actor handler, TextureRegion symbol, TextureRegion tex, String name,  int x, int y, int Scale, Animation... animations) {
		this(handler, symbol, tex, name, x, y, tex.getRegionWidth()*Scale, tex.getRegionHeight()*Scale, animations);
		this.setScale(Scale);
		this.setSrcWidth(tex.getRegionWidth());
		this.setSrcHeight(tex.getRegionHeight());
	}

	public BasicAbility(Actor handler, TextureRegion symbol, TextureRegion tex, String name,  int x, int y, int Width, int Height, Animation... animations) {
		super(handler, symbol, tex, x, y, Width, Height);
		super.setLoc(x, y);
		this.setName(name);
		this.setAnimations(animations);
		this.setIndex(0);
	}

	@Override
	public void render(SpriteBatch batch, BitmapFont font) {
		float presetX = font.getScaleX();
		float presetY = font.getScaleY();
		font.setScale(0.5f, 0.5f);
		batch.draw(getTex(), getLoc().x, getLoc().y, getWidth(), getHeight());
		if(getName().length()>16){
			font.draw(batch, getName(), (float) (getLoc().x+(1.25*Scale)), (float) (getLoc().y+(6.25*Scale)), 0, 16);
		} else {
			font.draw(batch, getName(), (float) (getLoc().x+(1.25*Scale)), (float) (getLoc().y+(6.25*Scale)));
		}
		batch.draw(getSymbol(), getLoc().x+(2*Scale), getLoc().y+(1*Scale), getSymbol().getRegionWidth()*(getScale()/3), getSymbol().getRegionHeight()*(getScale()/3));
		font.setScale(presetX, presetY);
	}

	@Override
	public void onClick(Actor target) {
		super.setTarget(target);
		super.setAbilityRunning(true);
		//Gdx.app.log("ABILITY", "Action " + this.getName() + " performed on "+target + "." + getAnimations().length+"L");
		if(getAnimations().length>0){
			getHandler().setAnimation(animations[getIndex()]);
			getHandler().runAnimiation();
		} else {
			//Gdx.app.log("ABI1LITY", "Action " + this.getName() + " performed on "+target + "." + getAnimations().length+"L");
			super.setAbilityRunning(false);
		}
	}
	
	@Override
	public void onRunning() {
		//Gdx.app.log("Ability", getName()+" is running."+getIndex()+"."+(getAnimations().length-1)+".");
		if(getHandler().getAnimation().isFinished()){
			increaseIndex();
			if(getAnimations().length-1>=getIndex()){
				//Gdx.app.log("Ability", getName()+" is starting a new animation."+getIndex()+"."+(getAnimations().length-1)+".");
				getHandler().setAnimation(getAnimation(getIndex()));
				getHandler().runAnimiation();
			} else {
				reset();
			}
		}
	}
	
	public void reset(){
		this.setIndex(0);
		super.setAbilityRunning(false);
		getHandler().setAnimation(getAnimation(getIndex()));
		for(Animation a: getAnimations()){
			a.reset();
		}
	}

	public int getSrcWidth() {
		return SrcWidth;
	}

	public void setSrcWidth(int srcWidth) {
		SrcWidth = srcWidth;
	}

	public int getSrcHeight() {
		return SrcHeight;
	}

	public void setSrcHeight(int srcHeight) {
		SrcHeight = srcHeight;
	}

	public int getScale() {
		return Scale;
	}

	public void setScale(int scale) {
		Scale = scale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Animation[] getAnimations() {
		return animations;
	}
	
	public Animation getAnimation(int i) {
		return getAnimations()[i];
	}

	public void setAnimations(Animation... animations) {
		this.animations = animations;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void increaseIndex() {
		this.index += 1;
	}
	
	public void increaseIndex(int index) {
		this.index += index;
	}
	
	

}
