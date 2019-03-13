package textExcel;

public class TextCell implements Cell{
	
	private String fullText = "";
	private String abbrevText = "";
	
	public TextCell(String text) {
		fullText = text;
		for(int i = 0; i < 10; i++) {
			if(text.charAt(i) > 0) {
				abbrevText += text.substring(i, i + 1);
			}else {
				abbrevText += " ";
			}
		}
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
