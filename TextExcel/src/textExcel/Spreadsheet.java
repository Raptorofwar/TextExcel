package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{

	private Cell[][] grid;
	private final int numRows = 20;
	private final int numCols = 12;
	
	public Spreadsheet() {
		grid = new Cell[numRows][numCols];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new EmptyCell();
			}
		}
	}
	
	@Override
	public String processCommand(String command)
	{
		// processes a user command, returns string to display, must be called in loop from main
		String result = "";
		return result;
	}

	@Override
	public int getRows()
	{
		// returns number of rows in grid
		return numRows;
	}

	@Override
	public int getCols()
	{
		// returns number of columns in grid
		return numCols;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// returns cell at loc
		return null;
	}

	@Override
	public String getGridText()
	{
		// returns entire grid, formatted as text for display
		return null;
	}

}
