package model.castle;

import java.util.ArrayList;

import model.actor.Monster;
import model.item.Item;
import view.GameScreen;

public class Floor{
	private Tile[][] tiles;
	private ArrayList<Monster> monsters;
	private ArrayList<Item> itens;
	private boolean darkMode;//in the future, a floor might be dark and the hero must use a light source to see around
	
	public Floor(boolean darkMode, String[] floorData, Monster... monsters) {
		this.darkMode = darkMode;
		tiles = new Tile[floorData.length][];
		for(int i = 0; i < floorData.length; i++) {
				tiles[i] = new Tile[floorData[i].length()];
			for(int j = 0; j < floorData[i].length(); j++) {
				switch(floorData[i].charAt(j)) {
				case '#':
					tiles[i][j] = new Tile(i,j,false,"wall");
					break;
				case 'H':
					tiles[i][j] = new Tile(i,j,true,"hero");
					break;
				case ' ':
					tiles[i][j] = new Tile(i,j,true,"corridor");
					break;
				case 'D':
					tiles[i][j] = new Tile(i,j,false,"door");
					break;
				}
			}
		}
		if(monsters != null) {
			this.monsters = new ArrayList<Monster>();
			for(Monster monster : monsters)
				this.monsters.add(monster);	
		}
	}
	public int getHeight() {
		return tiles.length;
	}
	public int getWidth() {
		return tiles[0].length;
	}
	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}
	
	public void checkInteractions(int posHeroX, int posHeroY) {
		System.out.println(tiles[0][8].getId());
		System.out.println(posHeroX);
		System.out.println(posHeroY);
		if(tiles[posHeroY][posHeroX].getId() == "door") {
			GameScreen.setMessage("The is a door around");
		}
		/*
		if(itens != null) {
			for(Item i : itens) {
				//get item position  and compare to hero position
				GameScreen.setMessage("The is an item around");
			}
	
		}*/
	}
	
	
	

}
