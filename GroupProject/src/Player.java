public class Player {

	private int targetRow;
	private int targetCol;
	private static int totalWalls = 10;
	private int numberOfWalls;
	private static int playersCreated = 0;
	private int playerNum;
	private Location currentLocation;
	private int playerType;

	public Player(Location currentLocation, int row) {
		this.currentLocation = currentLocation;
		playersCreated ++;
		playerNum = playersCreated;
		targetRow = row;
		playerType = 2;
	}
	
	public Player(Location currentLocation, int row, int col) {
		this.currentLocation = currentLocation;
		playersCreated ++;
		playerNum = playersCreated;
		targetRow = row;
		targetCol = col;
		playerType = 1;
	}
	
	public Player(int col, Location currentLocation) {
		this.currentLocation = currentLocation;
		playersCreated ++;
		playerNum = playersCreated;
		targetCol = col;
		playerType = 3;
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
	
	public int getTargetCol() {
		return targetCol;
	}
	
	public int getPlayertype() {
		return playerType;
	}

	public void decreaseNumberOfWalls() {
		numberOfWalls = numberOfWalls - 1;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setLocation(int row, int column) {
		currentLocation.setRow(row);
		currentLocation.setColumn(column);
	}
}