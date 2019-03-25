package textExcel;

public class PercentCell extends RealCell{
	
	private String text;
	
	public PercentCell(String input) {
		super(input.substring(0, input.indexOf(".")) + "%",
		Integer.parseInt(input.substring(0, input.indexOf("%"))) * .01);
		
		text = input;
	}
	
	public String abbreviatedCellText() {
		String temp;
		if(text.indexOf('.') > -1) {
			temp = text.substring(0, text.indexOf(".") + 1) + "%";
		}else {
			temp = (text + "          ").substring(0, 10);
		}
		return temp;
	}
	
	public String fullCellText() {
		String full = text.replace(".", "");
		full = full.replace("%", "");
		return full;
	}
}
