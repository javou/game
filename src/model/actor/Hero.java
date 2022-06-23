package model.actor;

import java.util.ArrayList;

import model.item.Item;
import model.item.HPPotion;
import model.item.Weapon;

public class Hero extends Actor {
	private static ArrayList<Item> items = new ArrayList<Item>();
	private boolean armorIsEquipped = true;
	private boolean weaponIsEquipped = false;
	
	public Hero(String name, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		super(name, hpMax, speed, armour, evasion, damage, posx, posy);		
	}
	
	public void addPotion() {
		items.add(new HPPotion(10));
	}
	
	public void usePotion() {
		for(Item item : items) {
			if(item.getClass() == HPPotion.class) {
				this.setHp(this.getHp() + ((HPPotion)item).getHitPoints());
				items.remove(item);
				break;
			}
		}
		
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
	
}
