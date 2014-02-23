package com.nolevelcap.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.fight.abilities.Ability;
import com.nolevelcap.world.Tile;

public abstract class Actor {
	private Scene scene;
	private TextureRegion tex;
	private boolean flipX, flipY;
	private Weapon weapon;
	protected int Health = 10;
	protected int Stamina = 10;
	private Vector2 StartingLoc;
	private Vector2 CurrentLoc;
	private Ability[] abilities;
	private boolean renderAbilities;
	private Animation currentAnimation;
	private Ability chosenAbility;
	
	public Actor(Scene scene){
		this.setScene(scene);
		this.setTexture(new TextureRegion());
		this.setFlipSettings(false, false);
		this.setLocation(0,0);
		this.setRenderAbilities(false);
		this.setAbilities(new Ability[1]);
		this.setAnimation(new Animation(this));
		this.setWeapon(new Weapon() {		
			@Override
			public void render(SpriteBatch batch, int shiftX, int shiftY) {
			}
			
			@Override
			public void logic() {
			}
		});
	}
	
	public void render(SpriteBatch batch, int XShift, int YShift, int Width, int Height){
		batch.draw(getTexture(), CurrentLoc.x+XShift, StartingLoc.y+YShift, Width, Height);
		getWeapon().render(batch, XShift, YShift);
	}
	public abstract void render(SpriteBatch batch);
	public abstract void logic();

	public abstract void animation();

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void pushed(Actor pusher) {
		moveBackwards(2);
		Gdx.app.log("Pushed", this + "is Pushed By" + pusher);
	}

	private void flip(TextureRegion tex, boolean flippedX, boolean flippedY){
		boolean toFlipX, toFlipY;
		//Gdx.app.log("A", tex+"");
		if(!tex.isFlipX()&&flippedX){
			toFlipX = true;
		} else if(tex.isFlipX()&&!flippedX){
			toFlipX = true;
		} else {
			toFlipX = false;
		}
		
		if(!tex.isFlipY()&&flippedY){
			toFlipY = true;
		} else if(tex.isFlipY()&&!flippedY){
			toFlipY = true;
		} else {
			toFlipY = false;
		}
		
		tex.flip(toFlipX, toFlipY);
	}
	
	public TextureRegion getTexture() {
		return tex;
	}

	public void setTexture(TextureRegion tex) {
		flip(tex, flipX, flipY);
		this.tex = tex;
	}
	
	public void setTexture(TextureRegion tex, boolean flipX, boolean flipY) {
		flip(tex, flipX, flipY);
		this.tex = tex;
	}
	
	public void setFlipSettings(boolean flipX, boolean flipY){
		this.flipX = flipX;
		this.flipY = flipY;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public boolean getFX(){
		return flipX;
	}
	
	public boolean getFY(){
		return flipY;
	}
	
	public void moveForwards(int count){

	}
	
	public void moveBackwards(int count){

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

	public void updateStamina(int stamina) {
		Stamina += stamina;
	}
	
	public void setStamina(int stamina) {
		Stamina += stamina;
	}

	public Vector2 getStartingLoc() {
		return StartingLoc;
	}

	public void setStartingLoc(Vector2 startingLoc) {
		StartingLoc = startingLoc;
	}
	
	public void setStartingLoc(int x, int y) {
		StartingLoc = new Vector2(x, y);
	}
	
	public void setLocation(int x, int y) {
		StartingLoc = new Vector2(x, y);
		setCurrentLoc(x, y);
	}

	public Vector2 getCurrentLoc() {
		return CurrentLoc;
	}

	public void setCurrentLoc(Vector2 currentLoc) {
		CurrentLoc = currentLoc;
	}
	
	public void setCurrentLoc(int x, int y) {
		CurrentLoc = new Vector2(x, y);
	}
	
	public void updateCurrentLoc(int x, int y) {
		CurrentLoc.x += x;
		CurrentLoc.y += y;
	}

	public Ability[] getAbilities() {
		return abilities;
	}

	public void setAbilities(Ability[] abilities) {
		this.abilities = abilities;
	}

	public boolean isRenderAbilities() {
		return renderAbilities;
	}

	public void setRenderAbilities(boolean renderAbilities) {
		this.renderAbilities = renderAbilities;
	}
	
	public void setAnimation(Animation animation){
		this.currentAnimation  = animation;
	}
	
	public Animation getAnimation(){
		return this.currentAnimation;
	}
	
	public void runAnimiation(){
		getAnimation().reset();
		getAnimation().setRunning(true);
	}
	
	public Ability getChosenAbility() {
		return chosenAbility;
	}

	public void setChosenAbility(Ability chosenAbility) {
		this.chosenAbility = chosenAbility;
	}

}
