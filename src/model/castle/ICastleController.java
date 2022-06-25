package model.castle;

import java.util.ArrayList;

import model.actor.IActor;

public interface ICastleController {
	public void updateCurrentFloor();
	public ArrayList<IActor> getFloorActors();
	public IActor getActorAtTile(int x, int y);
	public boolean isTileAtCurrentFloorOccupiable(int x, int y);
	public void setTileAtCurrentFloorOccupiable(int x, int y, boolean occupiable);
	public String typeAtTileInCurrentFloor(int x, int y);
	public void removeItemAtCurrentFloor(int x, int y);
	public void removeActorAtCurrentFloor(IActor actor);
	public IActor getHero();
}
