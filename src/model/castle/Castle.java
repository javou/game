package model.castle;

import java.util.ArrayList;
import java.util.Collections;

import model.actor.Hero;
import model.actor.IActor;
import resources.Constants;

public class Castle implements ICastle {
	private static Castle castle;
	private ArrayList<Floor>  floors;
	private int currentFloor = 0; //identify in which floor the hero currently is
	
	private Castle() {
		//the first floor is always the same and the others are shuffled randomly
		floors = new ArrayList<Floor>();
		ArrayList<Floor> aux = new ArrayList<Floor>();
		
		//starting floor
		Floor firstFloor = new Floor(true, Constants.FIRST_ROOM);
		floors.add(firstFloor);
		
		//level 1
		aux.add(new Floor(true, Constants.ENTRY_1));
		aux.add(new Floor(true, Constants.ENTRY_2));
		Collections.shuffle(aux);
		floors.add(aux.get(0));
		aux.clear();
		
		//level 2
		aux.add(new Floor(true, Constants.ENTRY_3));
		aux.add(new Floor(true, Constants.ENTRY_4));
		Collections.shuffle(aux);
		floors.add(aux.get(0));
		aux.clear();
		
		//final floor
		Floor finalFloor = new Floor(true, Constants.FINAL_ROOM);
		floors.add(finalFloor);
		
		IActor hero = Hero.getInstance();
		floors.get(0).addHero(hero);
	}
	
	public void restart() {
		//the first floor is always the same and the others are shuffled randomly
		floors = new ArrayList<Floor>();
		ArrayList<Floor> aux = new ArrayList<Floor>();
		
		//starting floor
		Floor firstFloor = new Floor(true, Constants.FIRST_ROOM);
		floors.add(firstFloor);
		
		//level 1
		aux.add(new Floor(true, Constants.ENTRY_1));
		aux.add(new Floor(true, Constants.ENTRY_2));
		Collections.shuffle(aux);
		floors.add(aux.get(0));
		aux.clear();
		
		//level 2
		aux.add(new Floor(true, Constants.ENTRY_3));
		aux.add(new Floor(true, Constants.ENTRY_4));
		Collections.shuffle(aux);
		floors.add(aux.get(0));
		aux.clear();
		
		//final floor
		Floor finalFloor = new Floor(true, Constants.FINAL_ROOM);
		floors.add(finalFloor);
		
		IActor hero = Hero.getInstance();
		hero.restart();
		floors.get(0).addHero(hero);
	}
	
	public static Castle getInstance() {
		if (castle == null) {
			castle = new Castle();
		}
		return castle;
	}
	
	public void shuffleFloors( ArrayList<Floor>  f) {
		Collections.shuffle(f);
	}
	
	public ArrayList<IActor> getFloorActors() {
		return floors.get(currentFloor).getActors();
	}
	
	public boolean isTileAtCurrentFloorOccupiable(int x, int y) {
		return floors.get(currentFloor).getTile(x, y).isOccupiable();
	}
	
	public void setTileAtCurrentFloorOccupiable(int x, int y, boolean occupiable) {
		floors.get(currentFloor).getTile(x, y).setOccupiable(occupiable);
	}
	
	public String typeAtTileInCurrentFloor(int x, int y) {
		return floors.get(currentFloor).getTileId(x, y);
	}
	
	public IActor getActorAtTile(int x, int y) {
		for(IActor actor:floors.get(currentFloor).getActors())
			if (actor.getPosX() == x && actor.getPosY() == y)
				return actor;
		return null;
	}

	public Floor getFloorAt(int index) {
		return floors.get(index);
	}
	
	public void updateCurrentFloor() {
		IActor hero = floors.get(currentFloor).getHero();
		// remove hero
		currentFloor += 1;
		floors.get(currentFloor).addHero(hero);
	}
	
	public void removeItemAtCurrentFloor(int x, int y) {
		floors.get(currentFloor).getTile(x, y).setId("corridor");
	}
	
	public void removeActorAtCurrentFloor(IActor actor) {
		floors.get(currentFloor).removeActor(actor);
	}
	
	public IFloor getCurrentFloor() {
		IFloor current = floors.get(currentFloor);
		return current;
	}
	
	public IActor getHero() {
		return floors.get(currentFloor).getHero();
	}
	
	/*
	public int getHeroPosX() {
		return floors.get(currentFloor).getHero().getPosX();
	}
	
	public int getHeroPosY() {
		return floors.get(currentFloor).getHero().getPosY();
	}
	*/
	
	/*
	public void moveHeroNextFloor() {
		currentFloor++;
	}
	*/
}