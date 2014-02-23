package com.nolevelcap.fight;

public class EnemySlot {
	private Enemy enemy;
	private boolean SlotActive;
	private int Level;
	
	public EnemySlot(){
		this.setSlotActive(false);
	}
	
	public EnemySlot(Enemy enemy, int lvl){
		this.setEnemy(enemy);
		this.setLevel(lvl);
	}

	public Enemy getEnemy() throws Exception {
		if(SlotActive){
			return enemy;
		} else {
			throw new Exception("Error trying to load enemy when slot is inactive");
		}
	}

	public void setEnemy(Enemy enemy) {
		if(enemy!=null){
			setSlotActive(true);
			this.enemy = enemy;
		}
	}

	public boolean isSlotActive() {
		return SlotActive;
	}

	private void setSlotActive(boolean slotActive) {
		SlotActive = slotActive;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}
}
