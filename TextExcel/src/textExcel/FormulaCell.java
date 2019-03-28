package textExcel;

public class FormulaCell extends RealCell{
	
	public FormulaCell(String input) {
		super(input, -1);
	}
	
	public double getDoubleValue(){
		int posStartParen = -1;
    	int posFinParen = -1;
    	String[] expression =
    			super.fullCellText().substring(1, super.fullCellText().length() - 1).split(" ");
    	int position = 0;
    	do {
    		if(expression[position].equals("(")) {
    			posStartParen = position;
    		}else if(expression[position].equals(")")) {
    			posFinParen = position;
    		}
    		if(posStartParen >= 0 && posFinParen >= 0) {
    			int length = posFinParen - posStartParen - 1;
    			String[] miniArray = new String[length];
    			for(int i = posStartParen + 1; i < posFinParen; i++) {
    				miniArray[i - posStartParen - 1] = expression[i];
    			}
    			expression[posStartParen] = evaluate(miniArray);
    			for(int i = posStartParen+1; i <= posFinParen; i++) {
    				remove(expression, posStartParen+1);
    			}
    			position = 0;
    			posStartParen = -1;
    			posFinParen = -1;
    		}
    		position++;
    	}while(position < expression.length);
    	return Double.parseDouble(evaluate(expression));
	}
	
    private String evaluate(String[] expression) {
    	
   	//takes in an expression in an array, returns the final result as an improper frac
        	
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
    	double term1 = Double.parseDouble(a);
    	char operator = b.charAt(0);
    	double term2 = Double.parseDouble(c);
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

}
