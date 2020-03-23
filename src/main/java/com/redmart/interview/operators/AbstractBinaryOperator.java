package main.java.com.redmart.interview.operators;

import java.util.EmptyStackException;
import java.util.Stack;

import main.java.com.redmart.interview.IOperator;
import main.java.com.redmart.interview.OperandMismatchException;

public abstract class AbstractBinaryOperator extends AbstractOperator implements IOperator 
{	
	public static final int BINARYOPCODELENGTH = 1;
	
	public AbstractBinaryOperator(String opcode) 
	{
		super(opcode, BINARYOPCODELENGTH);
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws OperandMismatchException 
	{			
		try 
		{
			Double rValue = stack.pop();
			Double lValue = stack.pop();
			
			Double value = Calculate(lValue, rValue);
			
			stack.push(value);
		}
		catch (EmptyStackException ex)
		{
			String message = String.format("not enough operands for the operator %s", token);
			
			logger.severe(message);
			throw new OperandMismatchException(message);
		}
	}	
	
	protected abstract double Calculate(double lValue, double rValue);
}
