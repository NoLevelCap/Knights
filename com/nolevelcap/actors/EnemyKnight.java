package com.nolevelcap.actors;

import com.badlogic.gdx.Gdx;
import com.nolevelcap.fight.FightScene;

public class EnemyKnight extends Knight{

	public EnemyKnight(FightScene scene) {
		super(scene, true, false, Gdx.graphics.getWidth()-(8*14), "Launcelot du Lac");
	}
	
}
