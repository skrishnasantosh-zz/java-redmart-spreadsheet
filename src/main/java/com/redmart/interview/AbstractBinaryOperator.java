package main.java.com.redmart.interview;

import java.util.EmptyStackException;
import java.util.Stack;

public abstract class AbstractBinaryOperator extends AbstractOperator
{	
	public static final int BINARYOPCODELENGTH = 1;
	
	public AbstractBinaryOperator(String opcode) 
	{
		super(opcode, BINARYOPCODELENGTH);
	}
	
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
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
			throw new FormulaEvaluatorException(message);
		}
	}	
	
	protected abstract double Calculate(double lValue, double rValue);
}
