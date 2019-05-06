package textExcel;

public abstract class RealCell implements Cell, Comparable{
	
	private String valueText;

	public RealCell(String text) {
		valueText = text;
	}
	
	public String abbreviatedCellText() {
		//returns first 10 chars. for grid use.
		
		return (this.getDoubleValue() + "          ").substring(0, 10);	
	}
	
	public String fullCellText() {
		// text for individual cell inspection, not truncated or padded
		return valueText;
	}
	
	public double getDoubleValue() {
		
		//GASP! THE VALUE!
		return Double.parseDouble(this.fullCellText());
	}
	
	public int compareTo(Object x) {
		if(this.getDoubleValue() == ((RealCell) x).getDoubleValue()) {
			return 0;
		}else if (this.getDoubleValue() > ((RealCell) x).getDoubleValue()){
			return 1;
		}else {
			return -1;
		}
	}
}
