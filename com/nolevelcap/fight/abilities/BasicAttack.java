package com.nolevelcap.fight.abilities;

import com.badlogic.gdx.files.FileHandle;
import com.nolevelcap.fight.Animation;
import com.nolevelcap.fight.MotionRegionCollection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nolevelcap.fight.Actor;

public class BasicAttack extends BasicAbility {
	
	private static final String name = "Basic Attack";
	private static MotionRegionCollection walking_forward, walking_backward;
	
	public BasicAttack(Actor handler, TextureRegion symbol, TextureRegion tex, Texture AnimationTexture, int x, int y, int Scale, int SrcWidth, int SrcHeight){
		super(handler, symbol, tex, name, x, y, Scale);
		walking_forward = new MotionRegionCollection(
				AnimationTexture, 
				SrcWidth, 
				SrcHeight, 
				new int[]{1, 2, 3, 4, 5,6}, 
				new int[]{0, 0, 0, 0, 0,0}, 
				new int[]{0, 3, 1, 2, 2,0}, 
				new int[]{0, 0, 0, 0, 0,0}, 
				new int[]{2, 1,-1,-1,-1,0}, 
				new int[]{0,-1, 1, 0, 0,0},
				Scale);
		
		walking_backward = new MotionRegionCollection(
				AnimationTexture, 
				SrcWidth, 
				SrcHeight, 
				new int[]{1, 2, 3, 4, 5,6}, 
				new int[]{0, 0, 0, 0, 0,0}, 
				new int[]{8, -3, -1, -2, -2,0},
				new int[]{0, 0, 0, 0, 0,0}, 
				new int[]{0, -100,-1,-1,1,1}, 
				new int[]{0,1, -1, 0, 0,0},
				Scale);
		super.setAnimations(new Animation(handler, walking_forward, 75, 3), new Animation(handler, walking_backward, 75, 3));
	}

}
