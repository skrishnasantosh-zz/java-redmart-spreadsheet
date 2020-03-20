package main.java.com.redmart.interview;

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
  