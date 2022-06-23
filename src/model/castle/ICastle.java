package model.castle;

import java.util.ArrayList;

import model.actor.IActor;

public interface ICastle {
	public boolean isTileAtCurrentFloorOccupiable(int x, int y);
	public ArrayList<IActor> getFloorActors();
}
