package main.java.com.redmart.interview;

import java.util.Stack;
import java.util.logging.Logger;

public class FormulaEvaluator 
{
	private static final Logger LOGGER = Logger.getLogger(FormulaEvaluator.class.getName());
	private String[] formula;
	private OperatorStrategy strategy;
	
	public FormulaEvaluator(String[] rpnFormula)
	{
		if (rpnFormula == null)
			throw new IllegalArgumentException();
		
		formula = rpnFormula;
		strategy = new OperatorStrategy();
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
		if (formula.length == 0)
			return null;
		
		Stack<Double> stack = new Stack<Double>();
				
		for (String token: formula)   
		{
			IOperator operator = strategy.getOperator(token);			
		
			String message = String.format("found operator of type %s for the value %s", operator.getClass().getName(), token);
			LOGGER.info(message);
			
			operator.operate(token, stack);
		}
		
		if (stack.size() != 1)
		{
			String message = String.format("formula evaluation failed as formula is not balanced", String.join(" ", formula));
						
			LOGGER.severe(message);
			throw new FormulaEvaluatorException(message);
		}
		
		return stack.pop();
	}
}
