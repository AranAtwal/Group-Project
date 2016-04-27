
public class Wall {
	
	private Location currentLocation;
	private static int totalWall = 10;
	private static int wallsCreated = 0;
	private int wallNum;
	
	
	public Wall(Location currentLocation) {
		this.currentLocation = currentLocation;
		wallsCreated ++;
		wallNum = wallsCreated;
	}
	
	public int wallNumber() {
		return wallNum;
	}
	
	public Location getLocation() {
		return currentLocation;
	}
}
