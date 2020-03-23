package main.java.com.redmart.interview.operators;

import main.java.com.redmart.interview.IOperator;

public class IncrementOperator extends AbstractUnaryOperator implements IOperator 
{
	private static final String OPCODE = "++";

	public IncrementOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double value) 
	{
		return ++value;
	}	
}
  