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
    	String command = cellName.toLowerCase();
    }

}
