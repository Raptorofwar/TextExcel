package textExcel;

public class PercentCell extends RealCell{
		
	public PercentCell(String input) {
		super(input);
	}
	
	public String fullCellText() {
		return Double.parseDouble
				(super.fullCellText().substring
						(0,super.fullCellText().length() - 1)) * .01 + "";
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return (super.fullCellText() + "          ").substring(0, 10);
	}
	
}
