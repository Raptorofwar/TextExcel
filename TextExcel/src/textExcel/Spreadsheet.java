package textExcel;

public class Spreadsheet implements Grid
{

	private Cell[][] sheet;
	private final int numRows = 20;
	private final int numCols = 12;
	
	public Spreadsheet() {
		sheet = new Cell[numRows][numCols];
		clearAll(sheet);
	}
	
	@Override
	public String processCommand(String command)
	{
		// processes a user command, returns string to display, must be called in loop from main
		String result = "";
    	String[] commandText = command.split(" ");
    	SpreadsheetLocation loc;
    	if(commandText[0].equalsIgnoreCase("clear")) {
    		if(commandText.length == 1) {
    			clearAll(sheet);
    		}else {
    			loc = new SpreadsheetLocation(commandText[1]);
    			sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
    		}
    	}else {
    		loc = new SpreadsheetLocation(commandText[0]);
    		sheet[loc.getRow()][loc.getCol()] = new TextCell(commandText[2]);
    	}
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
		return sheet[loc.getRow()][loc.getCol()];
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
				printGrid += sheet[rows][cols].abbreviatedCellText();
				printGrid += "|";
			}
		}
		return printGrid;
	}
	
	public void clearAll(Cell[][] grid) {
		for(int rows = 0; rows < getRows(); rows++) {
			for(int cols = 0; cols < getCols(); cols++) {
				grid[rows][cols] = new EmptyCell();
			}
		}
	}

}
