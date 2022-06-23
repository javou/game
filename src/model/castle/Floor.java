package model.castle;

import java.util.ArrayList;

import model.actor.Hero;
import model.actor.IActor;
import model.actor.Enemy;

public class Floor implements IFloor {
	private Tile[][] tiles; // tiles should be an nxn graph that holds information about what is in each position. It shall be used for enemy pathfinding. 
	private ArrayList<IActor> actors;
	private IActor hero;
	private boolean darkMode;//in the future, a floor might be dark and the hero must use a light source to see around
	
	public Floor(boolean darkMode, String[] floorData) {
		this.darkMode = darkMode;
		tiles = new Tile[floorData.length][];
		this.actors = new ArrayList<IActor>();
		
		for(int i = 0; i < floorData.length; i++) {
				tiles[i] = new Tile[floorData[i].length()]; // i -> y; j -> x
			for(int j = 0; j < floorData[i].length(); j++) {
				switch(floorData[i].charAt(j)) {
				case '#':
					tiles[i][j] = new Tile(j,i,false,"wall");
					break;
				case 'H':
					tiles[i][j] = new Tile(j,i,true,"hero");
					this.actors.add(new Hero("hero", 100, 10, 0, 0, 10, j, i));
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
					this.actors.add(new Enemy("1", 100, 10, 0, 0, 10, j, i));
					break;
				case '2':
					tiles[i][j] = new Tile(i,j,false,"2");
					this.actors.add(new Enemy("2", 100, 10, 0, 0, 10, j, i));
					break;
				case '3':
					tiles[i][j] = new Tile(i,j,false,"3");
					this.actors.add(new Enemy("3", 100, 10, 0, 0, 10, j, i));
					break;
				case '4':
					tiles[i][j] = new Tile(i,j,false,"4");
					this.actors.add(new Enemy("4", 100, 10, 0, 0, 10, j, i));
					break;
				case '5':
					tiles[i][j] = new Tile(i,j,false,"5");
					this.actors.add(new Enemy("5", 100, 10, 0, 0, 10, j, i));
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
	
	public String getTileId(int x, int y) {
		return tiles[y][x].getId();
	}
	
	public IActor getHero() {
		return hero;
	}
	
	public ArrayList<IActor> getActors() {
		return actors;
	}
	
	public void removeItem(int posX, int posY) {
		tiles[posY][posX].setId("corridor");
	}
}
