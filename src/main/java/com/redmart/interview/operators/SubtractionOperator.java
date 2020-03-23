package main.java.com.redmart.interview.operators;

import main.java.com.redmart.interview.IOperator;

public class SubtractionOperator extends AbstractBinaryOperator implements IOperator 
{
	private static final String OPCODE = "-";

	public SubtractionOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double lValue, double rValue) 
	{
		return lValue - rValue;
	}	
}
