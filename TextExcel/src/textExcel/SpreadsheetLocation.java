package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	
	private int rowNum = 0;
	private int colNum = 0;
	
    @Override
    public int getRow()
    {
        // TODO Auto-generated method stub
        return rowNum;
    }

    @Override
    public int getCol(){
        return colNum;
    }
    
    public SpreadsheetLocation(String cellName){
    	String command = cellName.toUpperCase();
    	colNum = command.charAt(0) - ('A');
    	rowNum = Integer.parseInt(command.substring(1)) - 1;
    }
    
    public SpreadsheetLocation(int row, int col){
    	//retained for easy coding?
    	rowNum = row;
    	colNum = col;
    }

}
