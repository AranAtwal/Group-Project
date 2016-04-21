
public class WallLocation extends Location {

	private int orientation;
	private static final int HORIZONTAL_WALL = 1;
	private static final int VERTICAL_WALL = 2;


	// Wall Constructor
	public WallLocation(int row, int column, int orientation) {
		super(row,column,orientation);
		setOrientation(orientation);
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public void setOrientation(int newOrientation) {
		orientation = newOrientation;
	}
}
