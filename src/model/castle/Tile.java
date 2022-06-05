package model.castle;

import model.actor.Monster;

public class Tile  {
	private int x, y;
	private String id; //wall, hero, monster, item...
	
	public Tile(int x, int y, String id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	

}
