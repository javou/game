package model.castle;

import java.util.ArrayList;

import model.actor.IActor;

public interface ICastleController {
	public boolean isTileAtCurrentFloorOccupiable(int x, int y);
	public IActor getActorAtTile(int x, int y);
	public ArrayList<IActor> getFloorActors();
}
