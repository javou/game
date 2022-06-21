package model.castle;

import java.util.ArrayList;
import java.util.Collections;

import model.actor.Monster;
import resources.Constants;

public class Castle implements ICastle {
	private ArrayList<Floor>  floors;
	private Floor firstFloor;//always the same
	private int currentFloor = 0;//identify which floor the hero is
	
	
	public Castle() {
		//the first floor is always the same and the other are shuffled randomly
		floors = new ArrayList<Floor>();
		firstFloor = new Floor(true, Constants.FIRST_ROOM);
		floors.add(new Floor(true, Constants.ENTRY_1));
		floors.add(new Floor(true, Constants.ENTRY_2));
		shuffleFloors();
		floors.add(0,firstFloor);
	}
	
	public void shuffleFloors() {
		Collections.shuffle(floors);
	}

	public Floor getFloorAt(int index) {
		return floors.get(index);
	}
	
	public Floor getNextFloor() {
		currentFloor += 1;
		return floors.get(currentFloor);
	}
	
	public Floor getCurrentFloor() {
		return floors.get(currentFloor);
	}
	public void moveHeroNextFloor() {
		currentFloor++;
	}
}