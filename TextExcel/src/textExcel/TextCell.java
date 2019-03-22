package textExcel;

public class TextCell implements Cell{
	
	private String fullText = "";
	
	public TextCell(String text) {
		fullText = text;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return (fullText.substring(1, fullText.length() - 1) + "          ").substring(0, 10);
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return fullText;
	}

}
