package textExcel;

public class EmptyCell implements Cell{
	
	private String content = "";
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return content;
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return content;
	}
}
