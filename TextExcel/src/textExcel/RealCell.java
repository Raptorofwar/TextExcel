package textExcel;

public abstract class RealCell implements Cell{
	
	private String valueText;

	public RealCell(String text) {
		valueText = text;
	}
	
	public String abbreviatedCellText() {
		return (this.getDoubleValue() + "          ").substring(0, 10);	
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return valueText;
	}
	
	public double getDoubleValue() {
		return Double.parseDouble(valueText);
	}
}
