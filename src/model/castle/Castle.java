package model.castle;

import java.util.ArrayList;
import java.util.Collections;

public class Castle {
	private ArrayList<Floor>  floors;
	private Floor firstFloor;
	private int currentFloor = 0;
	public Castle() {
		//the first floor is always the same and the other are shuffled randomly
		
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
	
}
