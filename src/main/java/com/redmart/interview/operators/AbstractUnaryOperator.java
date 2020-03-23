package main.java.com.redmart.interview.operators;

import java.util.Stack;

import main.java.com.redmart.interview.IOperator;
import main.java.com.redmart.interview.OperandMismatchException;

public abstract class AbstractUnaryOperator extends AbstractOperator implements IOperator 
{
	private static final int UNARYOPCODELENGTH = 2;
	
	public AbstractUnaryOperator(String opcode) 
	{
		super(opcode, UNARYOPCODELENGTH);
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws OperandMismatchException 
	{
		if (stack.size() < 1)
		{
			String message = String.format("not enough operands for the operator %s", token);
			
			logger.severe(message);			
			throw new OperandMismatchException(message);
		}
		
		Double value = stack.pop();		
		value = Calculate(value);
		
		stack.push(value);
	}	
	
	protected abstract double Calculate(double value);
}
