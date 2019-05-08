package textExcel;

import java.util.ArrayList;

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
    	SpreadsheetLocation loc;
    	if(command.length() >= 5 && command.substring(0,5).equalsIgnoreCase("clear")) {
    		if(command.length() == 5) {
    			clearAll(sheet);
    		}else {
    			loc = new SpreadsheetLocation(command.substring(6));
    			sheet[loc.getRow()][loc.getCol()] = new EmptyCell();
    		}
			return this.getGridText();
    	}else if (command.indexOf(" = ") > 0){
			loc = new SpreadsheetLocation(command.substring(0, command.indexOf(" = ")));
    		if(command.indexOf("\"") > -1) {
    			//make a text cell
    			sheet[loc.getRow()][loc.getCol()]
    					= new TextCell(command.substring(command.indexOf(" = ") + 3));
    		}else if (command.indexOf("(") > -1) {
    			//make a formula cell
    			sheet[loc.getRow()][loc.getCol()]
    					=new FormulaCell(command.substring(command.indexOf(" = ") + 3), this);
    		}else if(command.indexOf("%") > -1) {
    			//make a percent cell
    			sheet[loc.getRow()][loc.getCol()]
    					= new PercentCell(command.substring(command.indexOf(" = ") + 3));
    		}else {
    			//make a value cell
    			sheet[loc.getRow()][loc.getCol()]
    					=new ValueCell(command.substring(command.indexOf(" = ") + 3));
    		}
			return this.getGridText();
    	}else if(command.length() > 3 && (command.substring(0,5).equalsIgnoreCase("sorta") || command.substring(0,5).equalsIgnoreCase("sortd"))) {
			//SORTING! gah.
			ArrayList<Comparable> terms = new ArrayList<Comparable>();
			SpreadsheetLocation term1 = 
					new SpreadsheetLocation(command.substring(6, command.indexOf("-")));
			SpreadsheetLocation term2 = 
					new SpreadsheetLocation(command.substring(command.indexOf("-") + 1));
			
			for(int row  = term1.getRow(); row <= term2.getRow(); row++) {
				for(int col = term1.getCol(); col <= term2.getCol(); col++) {
					terms.add((Comparable) sheet[row][col]);
				}
			}
			
			int pos = 1;
			
			while(pos < terms.size()) {
				for(int i = 0; i < pos; i++) {
					if(terms.get(pos).compareTo(terms.get(i)) < 1) {
						terms.add(i, terms.remove(pos));
						i = pos;
					}
				}
				pos++;
			}
			
			int returnPos;
			
			if(command.substring(0,5).equalsIgnoreCase("sorta")) {
				returnPos = 0;
				for(int row  = term1.getRow(); row <= term2.getRow(); row++) {
    				for(int col = term1.getCol(); col <= term2.getCol(); col++) {
    					sheet[row][col] = (Cell) terms.get(returnPos);
    					returnPos++;
    				}
    			}
			}else {
				returnPos = terms.size() - 1;
				for(int row  = term1.getRow(); row <= term2.getRow(); row++) {
    				for(int col = term1.getCol(); col <= term2.getCol(); col++) {
    					sheet[row][col] = (Cell) terms.get(returnPos);
    					returnPos--;
    				}
    			}
			}
			return this.getGridText();
			
		}else {
    			loc = new SpreadsheetLocation(command.substring(0));
    			return this.getCell(loc).fullCellText();
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
		
		//self explanatory
		for(int rows = 0; rows < getRows(); rows++) {
			for(int cols = 0; cols < getCols(); cols++) {
				grid[rows][cols] = new EmptyCell();
			}
		}
	}

}
