package main.java.com.redmart.interview;

public class OperandMismatchException extends FormulaEvaluatorException 
{
	private static final long serialVersionUID = 2985200635089426126L;

	public OperandMismatchException(String message) 
	{
		super(message);		
	}
}
