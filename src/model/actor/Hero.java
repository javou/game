package model.actor;

import resources.Constants;

public class Hero extends Actor {
	
	public Hero() {
		hp = 100;
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
}
