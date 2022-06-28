package model.castle;

import java.util.ArrayList;

import model.actor.IActor;
import model.actor.Enemy;

public class Floor implements IFloor {
	private Tile[][] tiles; // tiles should be an nxn graph that holds information about what is in each position. It shall be used for enemy pathfinding. 
	private ArrayList<IActor> actors;
	private IActor hero;
	private int spawnX, spawnY;
	
	public Floor(String[] floorData) {
		tiles = new Tile[floorData.length][];
		this.actors = new ArrayList<IActor>();
		
		for(int y = 0; y < floorData.length; y++) {
				tiles[y] = new Tile[floorData[y].length()]; // i -> y; j -> x
			for(int x = 0; x < floorData[y].length(); x++) {
				switch(floorData[y].charAt(x)) {
				case '#':
					tiles[y][x] = new Tile(x,y,false,"wall");
					break;
				case 'H':
					tiles[y][x] = new Tile(x,y,false,"hero");
					spawnX = x;
					spawnY = y;
					break;
				case ' ':
					tiles[y][x] = new Tile(x,y,true,"corridor");
					break;
				case 'D':
					tiles[y][x] = new Tile(x,y,true,"door");
					break;
				case 'C':
					tiles[y][x] = new Tile(x,y,true,"chest");
					break;
				case '1':
					tiles[y][x] = new Tile(x,y,false,"1");
					this.actors.add(new Enemy("1", 200, 20, 0, 0, 22, x, y));
					break;
				case '2':
					tiles[y][x] = new Tile(x,y,false,"2");
					this.actors.add(new Enemy("2", 125, 7, 2, 0, 8, x, y));
					break;
				case '3':
					tiles[y][x] = new Tile(x,y,false,"3");
					this.actors.add(new Enemy("3", 50, 6, 0, 0, 10, x, y));
					break;
				case '4':
					tiles[y][x] = new Tile(x,y,false,"4");
					this.actors.add(new Enemy("4", 70, 10, 0, 0, 20, x, y));
					break;
				case '5':
					tiles[y][x] = new Tile(x,y,false,"5");
					this.actors.add(new Enemy("5", 150, 8, 5, 0, 30, x, y));
					break;
				case 'P':
					tiles[y][x] = new Tile(x,y,true,"potion");
					break;
				case 'A':
					tiles[y][x] = new Tile(x,y,true,"armour");
					break;
				case 'S':
					tiles[y][x] = new Tile(x,y,true,"shield");
					break;
				default:
					tiles[y][x] = new Tile(x,y,false,"error");
					break;
				}
			}
		}	
	}
	
	public void addHero(IActor hero) {
		hero.setPosX(spawnX);
		hero.setPosY(spawnY);
		actors.add(hero);
		this.hero = hero;
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public String getTileId(int x, int y) {
		return tiles[y][x].getId();
	}
	
	public boolean getHeroTrail(int x, int y) {
		if(x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length ){
			return tiles[y][x].isHeroPassedHere();	
		}
		return false;
		}
	
	public void setHeroTrail(int x, int y) {
		tiles[y][x].setHeroPassedHere(true);
	}
	
	public IActor getHero() {
		return hero;
	}
	
	public ArrayList<IActor> getActors() {
		return actors;
	}
	
	public void removeActor(IActor actor) {
		actors.remove(actor);
	}
	
	/*
	public void removeItem(int posX, int posY) {
		tiles[posY][posX].setId("corridor");
	}
	*/
}
