package model.actor;

import java.util.ArrayList;

import model.item.Item;
import model.item.HpPotion;

public class Hero extends Actor {
	private static Hero hero;
	private ArrayList<Item> items = new ArrayList<Item>();
	private int armourLevel = 0; 
	private boolean armorIsEquipped = false;
	private int weaponLevel = 0; 
	private boolean weaponIsEquipped = false;
	
	private Hero(String id, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		super(id, hpMax, speed, armour, evasion, damage, posx, posy);		
	}
	
	public static Hero getInstance() {
		if (hero == null) {
			hero = new Hero("hero", 100, 10, 0, 0, 10, 0, 0);
		}
		return hero;
	}
	
	public void restart() {
		hero = new Hero("hero", 100, 10, 0, 0, 10, 0, 0);
	}
	
	public int countPotions() {
		
		return items.size();//it works because we can get only potions as items.
		
	}

	public void addPotion() {
		items.add(new HpPotion(10));
	}
	
	public void usePotion() {
		for(Item item : items) {
			if(item.getClass() == HpPotion.class) {
				this.setHp(this.getHp() + ((HpPotion)item).getHitPoints());
				items.remove(item);
				break;
			}
		}
		
	}
	
	public void improveArmour() {
		this.armour += 3; // chest improves stats by a fixed amount
		armourLevel++;
	}
	
	public void improveDamage() {
		this.damage += 5;
		weaponLevel++;
	}
	
	public boolean isArmorIsEquipped() {
		return armorIsEquipped;
	}
	public void setArmorIsEquipped(boolean armorIsEquipped) {
		this.armorIsEquipped = armorIsEquipped;
	}
	public boolean isWeaponIsEquipped() {
		return weaponIsEquipped;
	}
	public void setWeaponIsEquipped(boolean swordIsEquipped) {
		this.weaponIsEquipped = swordIsEquipped;
	}

	public int getArmourLevel() {
		return armourLevel;
	}

	public int getWeaponLevel() {
		return weaponLevel;
	}
	
	/*
	public void equipItem(Item newItem) {
		for(Item item : items) {
			
			if(Weapon.class.isAssignableFrom(item.getClass())) {
				items.remove(item);
				items.add(newItem);
				this.setWeaponIsEquipped(true);
				break;
			}
			else if(Weapon.class.isAssignableFrom(item.getClass())) {
				items.remove(item);
				items.add(newItem);
				this.setArmorIsEquipped(true);
				break;
			}
		}
	}
	*/
}
