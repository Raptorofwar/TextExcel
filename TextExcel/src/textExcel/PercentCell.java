package textExcel;

public class PercentCell extends RealCell{
		
	public PercentCell(String input) {
		super(input.substring(0, input.indexOf("%")), Double.parseDouble(input.substring(0, input.indexOf("%"))));
	}
	
	public String abbreviatedCellText() {
		String temp = super.fullCellText();
		if(temp.indexOf('.') > -1) {
			temp = temp.substring(0, temp.indexOf("."));
		}
		temp += "%";
		return (temp + "          ").substring(0, 10);
	}
	
	public String fullCellText() {
		
	}
}
