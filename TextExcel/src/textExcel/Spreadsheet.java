package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{

	private Cell[][] grid;
	private final int numRows = 20;
	private final int numCols = 12;
	
	public Spreadsheet() {
		grid = new Cell[numRows][numCols];
		for(int rows = 0; rows < getRows(); rows++) {
			for(int cols = 0; cols < getCols(); cols++) {
				grid[rows][cols] = new EmptyCell();
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
		// returns number of rows in printGrid
		return numRows;
	}

	@Override
	public int getCols()
	{
		// returns number of columns in printGrid
		return numCols;
	}

	@Override
	public Cell getCell(Location loc) {
		// returns cell at loc
		return grid[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText() {
		// returns entire printGrid, formatted as text for display
		String printGrid = "   |";
		for(int cols = 0; cols < getCols(); cols++) {
			printGrid += Character.toString((char) ('A' + cols));
			for(int i = 0; i < 9; i++) {
				printGrid += " ";
			}
			printGrid += "|";
		}
		for(int rows = 0; rows < getRows(); rows++) {
			printGrid += "\n";
			printGrid += ((rows + 1) + " ");
			if ((rows + 1) / 10 == 0) {
				printGrid += " ";
			}
			printGrid += "|";
			for(int cols = 0; cols < getCols(); cols++) {
				printGrid += grid[rows][cols].abbreviatedCellText();
				printGrid += "|";
			}
		}
		return printGrid;
	}

}
