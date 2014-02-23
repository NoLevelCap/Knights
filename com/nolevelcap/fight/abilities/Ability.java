package com.nolevelcap.fight.abilities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.fight.Actor;
import com.nolevelcap.utils.*;

public abstract class Ability {
	private TextureRegion tex;
	private Rectangle rect;
	private int Width;
	private int Height;
	private TextureRegion symbol;
	private Vector2 loc;
	private Actor handler;
	private boolean abilitySelected;
	private boolean abilityRunning;
	private Timer timer;
	private Actor target;
	private int MaxLevel;
	
	public Ability(Actor handler, TextureRegion symbol, TextureRegion tex, float x, float y, int Width, int Height, int MaxLevel){
		this.setHandler(handler);
		this.setTex(tex);
		this.setSymbol(symbol);
		this.setRect(new Rectangle(x, y, Width, Height));
		this.setLoc(x, y);
		this.setHeight(Height);
		this.setWidth(Width);
		this.timer = new Timer();
		this.setMaxLevel(MaxLevel);
	}
	
	public abstract void render(SpriteBatch batch, BitmapFont font);
	public abstract void onClick(Actor target);
	public abstract void onRunning();
	

	public TextureRegion getTex() {
		return tex;
	}

	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}
	
	public Vector2 getLoc() {
		return loc;
	}

	public void setLoc(Vector2 loc) {
		this.loc = loc;
	}
	
	public void setLoc(float x, float y) {
		this.loc = new Vector2(x, y);
	}

	public TextureRegion getSymbol() {
		return symbol;
	}

	public void setSymbol(TextureRegion symbol) {
		this.symbol = symbol;
	}

	public Actor getHandler() {
		return handler;
	}

	public void setHandler(Actor handler) {
		this.handler = handler;
	}

	public boolean isAbilitySelected() {
		return abilitySelected;
	}

	public void setAbilitySelected(boolean abilitySelected) {
		this.abilitySelected = abilitySelected;
	}

	public boolean isAbilityRunning() {
		return abilityRunning;
	}

	public void setAbilityRunning(boolean abilityRunning) {
		this.abilityRunning = abilityRunning;
	}

	public Actor getTarget() {
		return target;
	}

	public void setTarget(Actor target) {
		this.target = target;
	}

	public int getMaxLevel() {
		return MaxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		MaxLevel = maxLevel;
	}

}
