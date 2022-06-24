package model.actor;

import java.util.ArrayList;
import java.util.Collections;

import model.item.Item;
import model.item.HpPotion;
import model.item.Weapon;

public class Hero extends Actor {
	private static Hero hero;
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean armorIsEquipped = false;
	private boolean weaponIsEquipped = false;
	
	public static Hero getInstance() {
		if (hero == null) {
			hero = new Hero("hero", 100, 10, 0, 0, 10, 0, 0);
		}
		return hero;
	}
	
	public int countPotions() {
		
		return items.size();//it works because we can get only potions as items.
		
	}
	
	private Hero(String name, int hpMax, int speed, int armour, int evasion, int damage, int posx, int posy) {
		super(name, hpMax, speed, armour, evasion, damage, posx, posy);		
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
	
	public void improveArmor(int n) {
		this.armour += n;
	}
	public void improveDamage(int n) {
		this.damage += n;
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
