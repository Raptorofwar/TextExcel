package textExcel;

public class PercentCell extends RealCell{
	
	public PercentCell(String input) {
		super(input.substring(0, input.indexOf(".")) + "%",
		Integer.parseInt(input.substring(0, input.indexOf("%"))) * .01);
	}
}
