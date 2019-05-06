package textExcel;

public class TextCell implements Cell, Comparable{
	
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
	
	public int compareTo(Object x) {
		int result;
		if(this.fullCellText().charAt(0) > ((TextCell) x).fullCellText().charAt(0)) {
			result = 1;
		}else if(this.fullCellText().charAt(0) == ((TextCell) x).fullCellText().charAt(0)){
			if(this.fullCellText().length() == 1) {
				result = 0;
			}else {
				result = new TextCell(this.fullCellText().substring(1)).compareTo(new TextCell(((TextCell) x).fullCellText().substring(1)));
			}
		}else {
			result = -1;
		}
		return result;
	}

}
