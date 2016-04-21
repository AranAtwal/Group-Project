
public class Location {
	private int row;
	private int column;
	
	//Pawn Location
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}
	// Wall Constructor
	public Location(int row, int column, int orientation) {
		this.row = row;
		this.column = column;
		
		WallLocation wallLocation = new WallLocation(row, column, orientation);
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
}
