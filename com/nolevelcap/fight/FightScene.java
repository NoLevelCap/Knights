package com.nolevelcap.fight;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.fight.abilities.Ability;
import com.nolevelcap.input.FightControls;
import com.nolevelcap.utils.Timer;
import com.nolevelcap.world.Tile;

public class FightScene extends Scene{
	
	SpriteBatch batch;
	ShapeRenderer debugbatch;
	BitmapFont font;
	Timer clock;
	private Player player;
	//private Enemy enemy1;
	//private Enemy enemy2;
	//private Enemy enemy3;
	private OrthographicCamera cam;
	private boolean isPlayer;
	private static final float CAMERA_WIDTH = Gdx.graphics.getWidth();
	private static final float CAMERA_HEIGHT = Gdx.graphics.getHeight();
	private Vector2 Ltap;
	private boolean PlayerTurn;
	private boolean abilityChosen;
	private ArrayList<EnemySlot> enemies;
	
	
	
	public FightScene(SpriteBatch batch){
		isPlayer = true;
		
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.translate(CAMERA_WIDTH/2, CAMERA_HEIGHT/2);
		this.cam.setToOrtho(true, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.update();
		
		Gdx.input.setInputProcessor(new GestureDetector(new FightControls(this)));
		
		this.font = new BitmapFont(Gdx.files.internal("font/font_1.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_1_0.png"))));
		this.debugbatch = new ShapeRenderer();
		this.batch = batch;
		batch.setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		this.clock = new Timer();
		this.Ltap = new Vector2(-1, -1);
		
		this.player = new Player(this, new Texture(Gdx.files.internal("knights/knight.png")), 75);
		this.enemies = new ArrayList<EnemySlot>();
		this.enemies.add(new EnemySlot(new Enemy(this, new Texture(Gdx.files.internal("knights/knight.png")), 375), 1));
		this.enemies.add(new EnemySlot(new Enemy(this, new Texture(Gdx.files.internal("knights/knight.png")), 525), 2));
		this.enemies.add(new EnemySlot(new Enemy(this, new Texture(Gdx.files.internal("knights/knight.png")), 675), 3));
		
	}
	
	public void render(){
		
		
		batch.begin();
		//font.draw(batch, player.toString(), 0, Gdx.graphics.getHeight()-font.getBounds("TEST").height);
		//player.render(batch);
		//enemy.render(batch);
		batch.end();
		
		try{
		batch.begin();
		font.draw(batch, "Player", player.getCurrentLoc().x-14, player.getCurrentLoc().y+player.Height+28);
		//font.draw(batch, "Enemy_1", enemies.get(0).getCurrentLoc().x-14, enemies.get(0).getCurrentLoc().y+enemies.get(0).Height+28);
		//font.draw(batch, "Enemy_2", enemies.get(1).getCurrentLoc().x-14, enemies.get(1).getCurrentLoc().y+enemies.get(1).Height+28);
		//font.draw(batch, "Enemy_3", enemies.get(2).getCurrentLoc().x-14, enemies.get(2).getCurrentLoc().y+enemies.get(2).Height+28);
		if(enemies.get(0).isSlotActive()){
			if(player.getChosenAbility().isAbilitySelected()&&abilityChosen&&enemies.get(0).getLevel()>player.getChosenAbility().getMaxLevel()){
				batch.setColor(Color.GRAY);
			}
		enemies.get(0).getEnemy().render(batch);
		batch.setColor(Color.WHITE);
		} if(enemies.get(1).isSlotActive()){
			if(player.getChosenAbility().isAbilitySelected()&&abilityChosen&&enemies.get(1).getLevel()>player.getChosenAbility().getMaxLevel()){
				batch.setColor(Color.GRAY);
			}
		enemies.get(1).getEnemy().render(batch);
		batch.setColor(Color.WHITE);
		} if(enemies.get(2).isSlotActive()){
			if(player.getChosenAbility().isAbilitySelected()&&abilityChosen&&enemies.get(2).getLevel()>player.getChosenAbility().getMaxLevel()){
				batch.setColor(Color.GRAY);
			}
		enemies.get(2).getEnemy().render(batch);
		batch.setColor(Color.WHITE);
		}
		player.render(batch);
		renderUI();
		batch.end();
	} catch(Exception e){
		e.printStackTrace();
	}
		
		
		
		
		if(clock.nextTick()){
			animations();
			logic();
			updateInputLocations();
		}
	}
	
	public void renderUI(){
		for(Ability a: player.getAbilities()){
			if(a.isAbilitySelected()){
				batch.setColor(0.50f, 0.00f, 0.00f, 0.50f);
			} else {
				//Gdx.app.log("A", a.getRect().x+" X; "+a.getRect().y+" Y; "+Ltap.x+" LX; "+Ltap.y+" LY; "+a.getRect().width+" W; "+a.getRect().height+" H; ");
			}
			a.render(batch, font);
			batch.setColor(1.00f, 1.00f, 1.00f, 1f);
			debugbatch.begin(ShapeType.Filled);
			//debugbatch.rect(a.getRect().x, a.getRect().y, a.getRect().width, a.getRect().height);
			debugbatch.end();
		}
	}
	
	public void updateInputLocations(){
		this.Ltap.x = Gdx.input.getX();
		this.Ltap.y = Gdx.graphics.getHeight()-Gdx.input.getY();
	}
	
	
	private void animations() {
		player.animation();
		//enemies.get(1).animation();
	}

	public void swipedLeft(){

	}
	
	public void swipedRight(){

	}
	
	
	public void touchDown(){	
	}
	
	public void tap(float x, float y){
		
		
		this.Ltap.x = x;
		this.Ltap.y = Gdx.graphics.getHeight()-y;
		//enemy1.change();
	}
	
	public void longTap(){
	}
	
	private void logic(){
		//Gdx.app.log("Dimensions", Gdx.graphics.getWidth()+" ,"+Gdx.graphics.getHeight()+" ,"+Gdx.graphics.getFramesPerSecond());
		checkAbilities();
		
	}
	
	private void checkAbilities(){
		for(Ability a: player.getAbilities()){
			if(a.isAbilityRunning()){
				a.onRunning();
			} else {
			updateInputLocations();
			if(Gdx.input.isTouched()&&(a.getRect().contains(Ltap))){
				a.setAbilitySelected(true);
				setAbilityChosen(true);
				player.setChosenAbility(a);
				for(Ability b: player.getAbilities()){
					if(!(a.equals(b))){
						b.setAbilitySelected(false);
					}
				}
			} else {
				//a.setAbilitySelected(false);
				//Gdx.app.log("A", a.getRect().x+" X; "+a.getRect().y+" Y; "+Ltap.x+" LX; "+Ltap.y+" LY; "+a.getRect().width+" W; "+a.getRect().height+" H; ");
			}
			
			
			if(a.isAbilitySelected()){
				for(EnemySlot e: enemies){
					try {
					if(Gdx.input.isTouched()&&e.getEnemy().getBounds().contains(Ltap)){
						a.onClick(e.getEnemy());
						setAbilityChosen(false);
						a.setAbilitySelected(false);
					}
					} catch (Exception z){
						z.printStackTrace();
					}
				}
			}
			}
		}
	}

	public boolean isAbilityChosen() {
		return abilityChosen;
	}

	public void setAbilityChosen(boolean abilityChosen) {
		this.abilityChosen = abilityChosen;
	}
	
	
	
	
}
