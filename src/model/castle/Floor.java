package model.castle;

import java.util.ArrayList;

import model.actor.Monster;
import model.item.Item;
import view.GameScreen;

public class Floor {
	private Tile[][] tiles;
	private ArrayList<Monster> monsters;
	private ArrayList<Item> itens;
	private boolean darkMode;//in the future, a floor might be dark and the hero must use a light source to see around
	
	public Floor(boolean darkMode, String[] floorData) {
		this.darkMode = darkMode;
		tiles = new Tile[floorData.length][];
		this.monsters = new ArrayList<Monster>();
		
		for(int i = 0; i < floorData.length; i++) {
				tiles[i] = new Tile[floorData[i].length()];
			for(int j = 0; j < floorData[i].length(); j++) {
				switch(floorData[i].charAt(j)) {
				case '#':
					tiles[i][j] = new Tile(j,i,false,"wall");
					break;
				case 'H':
					tiles[i][j] = new Tile(j,i,true,"hero");
					break;
				case ' ':
					tiles[i][j] = new Tile(j,i,true,"corridor");
					break;
				case 'D':
					tiles[i][j] = new Tile(j,i,true,"door");
					break;
				case 'C':
					tiles[i][j] = new Tile(j,i,false,"chest");
					break;
				case '1':
					tiles[i][j] = new Tile(j,i,false,"1");
					this.monsters.add(new Monster("1",j,i));
					break;
				case '2':
					tiles[i][j] = new Tile(i,j,false,"2");
					this.monsters.add(new Monster("2",j,i));
					break;
				case '3':
					tiles[i][j] = new Tile(i,j,false,"3");
					this.monsters.add(new Monster("3",j,i));
					break;
				case '4':
					tiles[i][j] = new Tile(i,j,false,"4");
					this.monsters.add(new Monster("4",j,i));
					break;
				case '5':
					tiles[i][j] = new Tile(i,j,false,"5");
					this.monsters.add(new Monster("5",j,i));
					break;
				case 'P':
					tiles[i][j] = new Tile(i,j,true,"potion");
					break;
				case 'A':
					tiles[i][j] = new Tile(i,j,true,"armour");
					break;
				case 'S':
					tiles[i][j] = new Tile(i,j,true,"shield");
					break;
				default:
					tiles[i][j] = new Tile(i,j,false,"error");
					break;
				}
			}
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
	
	public boolean lookFor(int posHeroX, int posHeroY, String target) {
		boolean output = false;
		if(tiles[posHeroY][posHeroX].getId() == target) {
			GameScreen.setMessage("The is a door around");
			output = true;
		}
		return output;
	}
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	
	public void removeItem(int posX, int posY) {
		tiles[posY][posX].setId("corridor");
	}
}
