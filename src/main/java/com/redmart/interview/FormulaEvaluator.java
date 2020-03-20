package main.java.com.redmart.interview;

import java.util.Stack;
import java.util.logging.Logger;

public class FormulaEvaluator 
{
	private static final Logger LOGGER = Logger.getLogger(FormulaEvaluator.class.getName());
	private String[] formula;
	
	public FormulaEvaluator(String[] rpnFormula)
	{
		if (rpnFormula == null)
			throw new IllegalArgumentException();
		
		formula = rpnFormula;
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
			//todo: operators
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
