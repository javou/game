package model.castle;

import java.util.ArrayList;

import model.actor.IActor;

public interface IFloor {
	public int getHeight();
	public int getWidth();
	public String getTileId(int x, int y);
	public IActor getHero();
	public ArrayList<IActor> getActors();
	public boolean getHeroTrail(int x, int y);
	public void setHeroTrail(int x, int y);
}
