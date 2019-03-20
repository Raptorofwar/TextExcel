package textExcel;

public class TextCell implements Cell{
	
	private String fullText = "";
	private String abbrevText = "";
	
	public TextCell(String text) {
		fullText = text;
		abbrevText = (fullText.substring(1, fullText.length() - 1) + "          ").substring(0, 10);
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return abbrevText;
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return fullText;
	}

}
