package main.java.com.redmart.interview;

public class DivisionOperator extends AbstractBinaryOperator implements IOperator 
{
	private static final String OPCODE = "/";

	public DivisionOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double lValue, double rValue) 
	{
		return lValue / rValue;
	}	
}
