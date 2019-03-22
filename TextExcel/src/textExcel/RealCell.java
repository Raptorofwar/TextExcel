package textExcel;

public abstract class RealCell implements Cell{
	
	private int value;
	private String valueText;
	
	public RealCell(String input) {
		value = Integer.parseInt(input);
		valueText = value + "";
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return (valueText.substring(1, valueText.length() - 1) + "          ").substring(0, 10);
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return value + "";
	}
	
	public double getDoubleValue() {
		return value;
	}
}
