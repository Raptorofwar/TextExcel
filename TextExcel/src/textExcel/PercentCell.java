package textExcel;

public class PercentCell extends RealCell{
		
	public PercentCell(String input) {
		super(input);
	}
	
	public String fullCellText() {
		String temp = 
				super.fullCellText().substring(0,super.fullCellText().indexOf("%"));
		temp = "000" + temp;
		if(temp.indexOf(".") == -1) {
			temp += ".";
		}
		temp = temp.substring(0, temp.indexOf(".") - 2) +
				"." +
				temp.substring(temp.indexOf(".") - 2, temp.indexOf(".")) +
				temp.substring(temp.indexOf(".") + 1);
		while(temp.charAt(0) == '0' && temp.charAt(1) != '.') {
			temp = temp.substring(1);
		}
		return temp;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(this.fullCellText());
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return (super.fullCellText() + "          ").substring(0, 10);
	}
}
