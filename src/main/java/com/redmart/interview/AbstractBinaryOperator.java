package main.java.com.redmart.interview;

import java.util.Stack;
import java.util.logging.Logger;

public abstract class AbstractBinaryOperator extends AbstractOperator implements IOperator 
{	
	public AbstractBinaryOperator(String opcode) 
	{
		super(opcode);
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
	{	
		if (stack.size() < 2)
		{
			String message = String.format("not enough operands for the operator %s", token);
			
			logger.severe(message);			
			throw new FormulaEvaluatorException(message);
		}
		
		Double rValue = stack.pop();
		Double lValue = stack.pop();
		
		Double value = Calculate(lValue, rValue);
		
		stack.push(value);
	}	
	
	protected abstract double Calculate(double lValue, double rValue);
}
