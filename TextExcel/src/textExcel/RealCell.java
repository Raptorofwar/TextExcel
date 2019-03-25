package textExcel;

public abstract class RealCell implements Cell{
	
	private double value;
	private String valueText;

	public RealCell(String text, double x) {
		valueText = text;
		value = x;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return (value + "          ").substring(0, 10);
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return valueText;
	}
	
	public double getDoubleValue() {
		return value;
	}
}
