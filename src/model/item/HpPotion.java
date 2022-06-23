package model.item;

public class HpPotion extends Consumable {
	private int hitPoints;
	
	public HpPotion(int hitPoints){
		this.hitPoints = hitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}
	
}
