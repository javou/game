package model.item;

public class Potion extends Consumable {
	private int hitPoints;
	
	public Potion(int hitPoints){
		this.hitPoints = hitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}
	
}
