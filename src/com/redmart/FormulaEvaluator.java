package com.redmart;

import java.util.Stack;
import java.util.logging.Logger;

public class FormulaEvaluator 
{
	private static final Logger LOGGER = Logger.getLogger(FormulaEvaluator.class.getName());
	
	private String[] formula;
	private OperatorStrategy strategy;
		
	public FormulaEvaluator(String[] formula)
	{
		LOGGER.setUseParentHandlers(false);
		strategy = new OperatorStrategy();
		
		this.formula = formula;
		
		if (formula == null)
			throw new IllegalArgumentException();
	}
	
	public boolean hasCellReference()
	{
		for (String token: formula)
		{
			if (token.length() > 0 && Character.isLetter(token.charAt(0)))
				return true;			
		} 
		
		return false;
	}
	
	public Double evaluate() throws FormulaEvaluatorException
	{
		if (formula == null)
			throw new IllegalArgumentException();
		
		if (formula.length == 0)
			return null;
		
		Stack<Double> stack = new Stack<Double>();
				
		for (String token: formula)   
		{
			IOperator operator = strategy.getOperator(token);

			if (operator == null)
				continue;
				
			String message = String.format("found operator of type %s for the value %s", operator.getClass().getName(), token);
			LOGGER.info(message);
			
			logStack(token, stack);
			
			operator.operate(token, stack);
		}
		
		int stackSize = stack.size();		
		if (stackSize != 1)
		{
			String message = String.format("formula evaluation failed as formula is not balanced", String.join(" ", formula));
						
			LOGGER.severe(message);
			throw new FormulaEvaluatorException(message);
		}
		
		return stack.pop();
	}
	
	private void logStack(String token, Stack<Double> stack) 
	{
		StringBuilder messageBuilder = new StringBuilder();
		
		messageBuilder.append("Stack : ");
				
		for (Double d : stack) 
		{
			messageBuilder.append(d);
			messageBuilder.append(", ");
		}
			
		messageBuilder.append(String.format(" Operator : %s", token));
		
		String message = messageBuilder.toString();
		
		LOGGER.info(message);		
	}
}
