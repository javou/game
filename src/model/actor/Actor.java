package model.actor;

public class Actor implements IActor {
	protected String id = "";
	protected String name = "";
	protected int hp, hpMax, speed, damage, armour, evasion;
	protected int energy = 0;
	protected int posx, posy;
	
	public Actor(String id, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		this.id = id;
		this.hpMax = hpMax;
		hp = hpMax;
		this.speed = speed;
		this.armour = armour;
		this.evasion = evasion;
		this.damage = damage;
		this.posx = posx;
		this.posy = posy;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = this.name + name;
	}
	public void removeLetterName() {
		if(this.name.length() > 0)
			this.name = this.name.substring(0, this.name.length()-1);
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

	public void usePotion() {}

	public void addPotion() {}

	public void improveArmour() {}

	public void improveDamage() {}
	
	public boolean isArmorIsEquipped() {
		return false;
	}

	public void setArmorIsEquipped(boolean armorIsEquipped) {}

	public boolean isWeaponIsEquipped() {
		return false;
	}

	public void setWeaponIsEquipped(boolean swordIsEquipped) {}
	
	
	public int countPotions() {
		return 0;
	}

	// flaws
	public boolean isHeroSeen() {
		return false;
	}
	
	public void setHeroSeen(boolean heroSeen) {}
	
	public void restart() {}

	@Override
	public int getArmourLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWeaponLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
