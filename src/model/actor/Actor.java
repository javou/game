package model.actor;

public class Actor {
	private int hp, speed, armour, evasion;
	private int damage, armourPiercing, blockChance; // weapon exclusive
	private int energy = 0;
	
	public Actor() {
		
	}
	
	public int getSpeed() {
		return speed;
	}
}
