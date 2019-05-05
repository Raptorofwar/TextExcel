package textExcel;

public class FormulaCell extends RealCell{
	
	private Spreadsheet s;
	
	public FormulaCell(String input, Spreadsheet s) {
				
		super(input);
		
		this.s = s;
	}
	
	public double getDoubleValue(){
    	
		//Takes in entire formula (full cell text) , returns double equivalent
		
    	String[] expression =
    			this.fullCellText().substring(2, this.fullCellText().length() - 2).toUpperCase().split(" ");
    	
    	if(expression[0].equals("SUM") || expression[0].equals("AVG")){
    		SpreadsheetLocation term1 = new SpreadsheetLocation(expression[1].substring(0, expression[1].indexOf("-")));
    		SpreadsheetLocation term2 = new SpreadsheetLocation(expression[1].substring(expression[1].indexOf("-") + 1));
    		double ans = 0;
    		
    		for(int row = term1.getRow(); row <= term2.getRow(); row++) {
    			for(int col = term1.getCol(); col <= term2.getCol(); col++) {
    				ans += ((RealCell) s.getCell(new SpreadsheetLocation(row, col))).getDoubleValue();
    			}
    		}
    		
    		if(expression[0].equals("AVG")) {
    			ans /= ((Math.abs(term1.getRow() - term2.getRow()) + 1) * (Math.abs(term1.getCol() - term2.getCol()) + 1));
    		}
    		
    		return ans;
    		
    	}else {
    		return Double.parseDouble(evaluate(expression));
    	}
	}
	
    private String evaluate(String[] expression) {
    	
   	//takes in an expression in an array, returns the final result
        	
        String[] evaluating = expression;
        for(int i = 0; i<evaluating.length; i++) {
        	if(evaluating[i].equals("*")||evaluating[i].equals("/")) {
        		evaluating[i-1] = operation(evaluating[i-1], evaluating[i], evaluating[i+1]);
        		evaluating = remove(evaluating, i);
        		evaluating = remove(evaluating, i);
        		i = 0;
        	}
        }
        for(int i = 0; i<evaluating.length; i++) {
        	if(evaluating[i].equals("-")||evaluating[i].equals("+")) {
        		evaluating[i-1] = operation(evaluating[i-1], evaluating[i], evaluating[i+1]);
        		evaluating = remove(evaluating, i);
        		evaluating = remove(evaluating, i);
        		i = 0;
        	}
        }
        return evaluating[0];
    }
    	    
    private String[] remove(String[] arr, int idx) {
    	
    // removes element in array arr at index idx
        
    	String[] removed = new String[arr.length-1];
    	int count=0;
    	for(int i=0; i<arr.length; i++) {
			if(i!=idx) {
    			removed[count]=arr[i];
    			count++;
    		}
		}
    	return removed;
    }
    
    private String operation(String a, String b, String c) {
    	
    	//Does operation
    	double term1;
    	double term2;
    	
    	if(a.length() > 1 && isALetter(a.charAt(0))) {
    		term1 = ((RealCell) s.getCell(new SpreadsheetLocation(a))).getDoubleValue();
    	}else {
    		term1 = Double.parseDouble(a);
    	}
    	
    	if(c.length() > 1 && isALetter(c.charAt(0))) {
    		term2 = ((RealCell) s.getCell(new SpreadsheetLocation(c))).getDoubleValue();
    	}else {
    		term2 = Double.parseDouble(c);
    	}
    	
    	char operator = b.charAt(0);
    	double answer = 0;
    	
    	if(operator == '*') {
    		answer = term1 * term2;
    	}else if(operator == '/') {
    		answer = term1 / term2;
    	}else if(operator == '+') {
    		answer = term1 + term2;
    	}else if(operator == '-') {
    		answer = term1 - term2;
    	}
    	return answer + "";
    }
    
    public static boolean isALetter(char x) {
    	
    	//tests if x is a letter, capital or lowercase
    	if((x >= 65 && x <=90) || (x >= 97 && x <= 122)) {
    		return true;
    	}else {
    		return false;
    	}
    }

}
