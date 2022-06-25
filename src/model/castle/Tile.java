package model.castle;

public class Tile {
	private int x, y;

	private boolean occupiable; //wall/actor or free(occupiable space)
	private boolean visible;
	private String id; //wall, door, item
	private boolean heroPassedHere;
	
	public Tile(int x, int y, boolean occupiable, String id) {
		this.x = x;
		this.y = y;
		this.occupiable = occupiable;
		this.visible = true; //for the moment, we have day light in the room
		this.id = id;
		this.heroPassedHere = false;
	}

	public boolean isHeroPassedHere() {
		return heroPassedHere;
	}

	public void setHeroPassedHere(boolean heroPassedHere) {
		this.heroPassedHere = heroPassedHere;
	}

	public String getId() {
		return id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isOccupiable() {
		return occupiable;
	}
	
	public void setOccupiable(boolean occupiable) {
		this.occupiable = occupiable;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
