package com.nolevelcap.actors;

public abstract class Actor {
	public Actor() {
	}
	
	public abstract void render();
	
	public abstract void create();
	
	public abstract void logic();
	
	public abstract void animation();
	
	public abstract void move(MoveID move);
}
