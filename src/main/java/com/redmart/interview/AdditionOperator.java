package main.java.com.redmart.interview;

import java.util.Stack;

public class AdditionOperator extends AbstractBinaryOperator implements IOperator 
{
	private static final String OPCODE = "+";

	public AdditionOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double lValue, double rValue) 
	{
		return lValue + rValue;
	}	
}
