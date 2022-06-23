package model.castle;

import java.util.ArrayList;

import model.actor.IActor;

public interface ICastle extends ICastleController, ICastleView  {
	public boolean isTileAtCurrentFloorOccupiable(int x, int y);
	public IActor getActorAtTile(int x, int y);
	public ArrayList<IActor> getFloorActors();
	public IFloor getCurrentFloor();
	public String typeAtTile(int x, int y);
}
