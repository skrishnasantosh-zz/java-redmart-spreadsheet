package main.java.com.redmart.interview;

public class SubtractionOperator extends AbstractBinaryOperator
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
