package model.castle;

public class Tile {
	private int x, y;

	private boolean occupiableSpace;//wall or free(occupiable space)
	private boolean visible;
	private String id;//wall, door, item
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
