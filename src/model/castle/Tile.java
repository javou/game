package model.castle;

import model.actor.Actor;
import model.item.Item;

public class Tile {
	private int x, y;
	private Actor actor;
	private Item item;
	private boolean occupiableSpace;//wall or free(occupiable space)
	private boolean visible;
	private String id;
	public Tile(int x, int y, boolean occupiableSpace, String id) {
		this.x = x;
		this.y = y;
		this.occupiableSpace = occupiableSpace;
		this.visible = true;//for the moment, we have day light in the room
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isOccupiableSpace() {
		return occupiableSpace;
	}
	
	
	

}
