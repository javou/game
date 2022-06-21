package model.item;

public class HPPotion extends Consumable {
	private int hitPoints;
	
	public HPPotion(int hitPoints){
		this.hitPoints = hitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}
	
}
