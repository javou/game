package model.actor;

public class Enemy extends Actor {
	private boolean heroSeen = false;

	public Enemy(String id, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		super(id, hpMax, speed, armour, evasion, damage, posx, posy);		
	}
	
	public boolean isHeroSeen() {
		return heroSeen;
	}
	
	public void setHeroSeen(boolean heroSeen) {
		this.heroSeen = heroSeen;
	}
}
