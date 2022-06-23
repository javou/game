package model.actor;

public class Actor implements IActor {
	protected String name = "";
	protected int hp, hpMax, speed, armour, evasion;
	protected int damage; // weapon exclusive
	protected int energy = 0;
	protected int posx, posy;
	
	public Actor(String name, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		this.name = name;
		this.hpMax = hpMax;
		hp = hpMax;
		this.speed = speed;
		this.armour = armour;
		this.evasion = evasion;
		this.damage = damage;
		this.posx = posx;
		this.posy = posy;
	}
	
	public String getName() {
		return name;
	}

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
		if(hp > this.hp) 
			this.hp = hpMax;
		else
			this.hp = hp;
	}

	public int getArmour() {
		return armour;
	}

	public int getDamage() {
		return damage;
	}
	
}
