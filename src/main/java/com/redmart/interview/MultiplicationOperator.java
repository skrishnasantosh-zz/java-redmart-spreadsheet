package main.java.com.redmart.interview;

public class MultiplicationOperator extends AbstractBinaryOperator implements IOperator 
{
	private static final String OPCODE = "*";

	public MultiplicationOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double lValue, double rValue) 
	{
		return lValue * rValue;
	}	
}
