package model.castle;

import model.actor.IActor;

public interface IFloor {
	public int getHeight();
	public int getWidth();
	public String getTileId(int x, int y);
	public IActor getHero();
}
