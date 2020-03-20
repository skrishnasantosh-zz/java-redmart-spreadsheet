package main.java.com.redmart.interview;

public class DecrementOperator extends AbstractUnaryOperator implements IOperator 
{
	private static final String OPCODE = "--";

	public DecrementOperator() 
	{
		super(OPCODE);
	}
	
	@Override
	protected double Calculate(double value) 
	{
		return --value;
	}	
}
  