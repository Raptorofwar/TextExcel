package textExcel;

public class PercentCell extends RealCell{
		
	public PercentCell(String input) {
		super(input.substring(0, input.length()), Double.parseDouble(input.substring(0, input.indexOf("%"))) * .01);
	}
	
	public String abbreviatedCellText() {
		String temp = super.fullCellText();
		if(temp.indexOf('.') > -1) {
			temp = temp.substring(0, temp.indexOf(".")) + "%";
		}
		if(temp.equals("%") || temp.equals("-%")) {
			temp = "0%";
		}
		return (temp + "          ").substring(0, 10);
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
}
