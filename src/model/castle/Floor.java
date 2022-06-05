package model.castle;

import java.util.ArrayList;

import model.actor.Monster;

public class Floor{
	private Tile[][] tiles;
	private ArrayList<Monster> monsters;
	private boolean darkMode;//in the future, a floor might be dark and the hero must use a light source to see around
	
	public Floor(boolean darkMode, String[] floorData, Monster... monsters) {
		this.darkMode = darkMode;
		tiles = new Tile[floorData.length][];
		for(int i = 0; i < floorData.length; i++) {
				tiles[i] = new Tile[floorData[i].length()];
			for(int j = 0; j < floorData[i].length(); j++) {
				switch(floorData[i].charAt(j)) {
				case '#':
					tiles[i][j] = new Tile(i,j,"wall");
				}
				
			}
		}
		
		this.monsters = new ArrayList<Monster>();
		for(Monster monster : monsters)
			this.monsters.add(monster);		
	}

}
