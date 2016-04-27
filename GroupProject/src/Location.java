
public class Location {
	private int row;
	private int column;
	private int orientation;
	private int wallType;
	
	//Pawn Location
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}
	// Wall Constructor
	public Location(int row, int column, int orientation) {
		this.row = row;
		this.column = column;
		this.orientation = orientation;
		
//		WallLocation wallLocation = new WallLocation(row, column, orientation);
		if (orientation == 1) {
			wallType = 8;
		}
		else if (orientation == 2) {
			wallType = 8;
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setRow(int newRow) {
		row = newRow;
	}
	
	public void setColumn(int newColumn) {
		column = newColumn;
	}
	
	public int getOrientation() {
		return wallType;
	}
}
