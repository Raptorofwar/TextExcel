package textExcel;

public class FormulaCell extends RealCell{
	
	private Spreadsheet s;
	
	public FormulaCell(String input, Spreadsheet s) {
		
		//only the input text is useful here; the actual value inputted is just filler
		
		super(input, -1);
		
		this.s = s;
	}
	
	public double getDoubleValue(){
    	
		//Takes in entire formula, returns double equivalent
		
    	String[] expression =
    			super.fullCellText().substring(2, super.fullCellText().length() - 2).toUpperCase().split(" ");
    	
    	if(expression[0].equals("SUM") || expression[0].equals("AVG")){
    		String term1 = expression[1].substring(0, expression[1].indexOf("-"));
    		String term2 = expression[1].substring(expression[1].indexOf("-") + 1);
    		
    		int top = Integer.parseInt(term1.substring(1));
    		int left = Integer.parseInt(term1.substring(0, 1)) - 'A';
    		int bottom = Integer.parseInt(term2.substring(1));
    		int right = Integer.parseInt(term2.substring(0, 1)) - 'A';
    		
    		int ans = 0;
    		
    		for(int row = left; row <= right; row++) {
    			for(int col = top; top <= bottom; top++) {
    				ans += ((RealCell) s.getCell(new SpreadsheetLocation(row, col))).getDoubleValue();
    			}
    		}
    		
    		if(expression[0].equals("AVG")) {
    			ans /= ((bottom - top + 1) * (right - left + 1));
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
    		term1 = ((RealCell) s.getCell(new SpreadsheetLocation(a.charAt(0) - 'A',
    				Integer.parseInt(a.substring(1)) - 1))).getDoubleValue();
    	}else {
    		term1 = Double.parseDouble(a);
    	}
    	
    	if(c.length() > 1 && isALetter(c.charAt(0))) {
    		term2 = ((RealCell) s.getCell(new SpreadsheetLocation((c.charAt(0) - 'A'),
    				Integer.parseInt(c.substring(1)) - 1))).getDoubleValue();
    	}else {
    		term2 = Double.parseDouble(c);
    	}
    	
    	char operator = b.charAt(0);
    	term2 = Double.parseDouble(c);
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
    
    public String abbreviatedCellText() {
    	return (this.getDoubleValue() + "          ").substring(0, 10);
    }
    
    public static boolean isALetter(char x) {
    	if((x >= 65 && x <=90) || (x >= 97 && x <= 122)) {
    		return true;
    	}else {
    		return false;
    	}
    }

}
