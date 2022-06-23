package model.castle;

import java.util.ArrayList;
import java.util.Collections;

import model.actor.IActor;
import resources.Constants;

public class Castle implements ICastle {
	private static Castle castle;
	private ArrayList<Floor>  floors;
	private Floor firstFloor; //always the same
	private int currentFloor = 0; //identify in which floor the hero currently is
	
	private Castle() {
		//the first floor is always the same and the other are shuffled randomly
		floors = new ArrayList<Floor>();
		firstFloor = new Floor(true, Constants.FIRST_ROOM);
		floors.add(new Floor(true, Constants.ENTRY_1));
		floors.add(new Floor(true, Constants.ENTRY_2));
		shuffleFloors();
		floors.add(0, firstFloor);
	}
	
	public static Castle getInstance() {
		if (castle == null) {
			castle = new Castle();
		}
		return castle;
	}
	
	public void shuffleFloors() {
		Collections.shuffle(floors);
	}
	
	public ArrayList<IActor> getFloorActors() {
		return floors.get(currentFloor).getActors();
	}
	
	public boolean isTileAtCurrentFloorOccupiable(int x, int y) {
		return floors.get(currentFloor).getTile(x, y).isOccupiableSpace();
	}
	
	public String typeAtTile(int x, int y) {
		return floors.get(currentFloor).getTileId(x, y);
	}
	
	public IActor getActorAtTile(int x, int y) {
		
	}

	public Floor getFloorAt(int index) {
		return floors.get(index);
	}
	
	public Floor getNextFloor() {
		currentFloor += 1;
		return floors.get(currentFloor);
	}
	
	public IFloor getCurrentFloor() {
		IFloor current = floors.get(currentFloor);
		return current;
	}
	
	public void moveHeroNextFloor() {
		currentFloor++;
	}
}