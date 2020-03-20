package main.java.com.redmart.interview;

import java.util.Stack;

public abstract class AbstractUnaryOperator extends AbstractOperator implements IOperator 
{
	private static final int UNARYOPCODELENGTH = 2;
	
	public AbstractUnaryOperator(String opcode) 
	{
		super(opcode, UNARYOPCODELENGTH);
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
	{
		if (stack.size() < 1)
		{
			String message = String.format("not enough operands for the operator %s", token);
			
			logger.severe(message);			
			throw new FormulaEvaluatorException(message);
		}
		
		Double value = stack.pop();		
		value = Calculate(value);
		
		stack.push(value);
	}	
	
	protected abstract double Calculate(double value);
}
