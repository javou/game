package model.actor;

import java.util.ArrayList;

import model.item.Item;
import model.item.Potion;
import model.item.Weapon;
import resources.Constants;

public class Hero extends Actor {
	private static ArrayList<Item> items = new ArrayList<Item>();
	private boolean armorIsEquipped = true;
	private boolean weaponIsEquipped = false;
	
	public Hero() {
		hp = 100;
		hpMax = 100;
		speed = 10;
		armour = 0;
		evasion = 0;
		damage = 5;
		
		for(int y = 0; y < Constants.FIRST_ROOM.length; y++) {
			int x = Constants.FIRST_ROOM[y].indexOf("H",0);
			if(x > 0 ) {
				posx = x;
				posy = y;
				break;
				
			}
		}
	}
	public void addPotion() {
		items.add(new Potion(10));
	}
	
	public void usePotion() {
		for(Item item : items) {
			if(item.getClass() == Potion.class) {
				this.setHp(this.getHp() + ((Potion)item).getHitPoints());
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
