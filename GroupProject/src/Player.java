public class Player {

	private int targetRow;
	private static int totalWalls = 10;
	private int numberOfWalls;
	private Location currentLocation;
	//private PlayerType playerType;

	public Player(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getNumberOfWalls() {
		return numberOfWalls;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public int getTargetRow() {
		return targetRow;
	}

	public void decreaseNumberOfWalls() {
		numberOfWalls = numberOfWalls - 1;
	}
}