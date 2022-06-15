package model.actor;

public class Actor {
	protected int hp, speed, armour, evasion;
	protected int damage; // weapon exclusive
	protected int energy = 0;
	protected int posx, posy;
	
	public int getSpeed() {
		return speed;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getPosX() {
		return posx;
	}
	
	public void setPosX(int posx) {
		this.posx = posx;
	}
	
	public int getPosY() {
		return posy;
	}
	
	public void setPosY(int posy) {
		this.posy = posy;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}



	public int getArmour() {
		return armour;
	}
	
}
