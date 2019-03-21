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
    	SpreadsheetLocation loc;
    	if(command.length() >= 5 && command.substring(0,5).equalsIgnoreCase("clear")) {
    		if(command.length() == 5) {
    			clearAll(sheet);
    			return this.getGridText();
    		}else {
    			loc = new SpreadsheetLocation(command.substring(6));
    			sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
    			return this.getGridText();
    		}
    	}else {
    		if(command.indexOf(" = ") > 0) {
    			loc = new SpreadsheetLocation(command.substring(0, command.indexOf(" = ")));
    			sheet[loc.getRow()][loc.getCol()]
    					= new TextCell(command.substring(command.indexOf("\""), command.lastIndexOf("\"") + 1));
    			return this.getGridText();
    		}else {
    			loc = new SpreadsheetLocation(command.substring(0));
    			return this.getCell(loc).fullCellText();
    		}
    	}
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
		printGrid += "\n";
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
