package com.redmart.operators;

import java.util.Stack;
import java.util.logging.Logger;
import com.redmart.*;

import com.redmart.exceptions.FormulaEvaluatorException;

public class NumericConstantOperator implements IOperator 
{
	private static final Logger LOGGER = Logger.getLogger(NumericConstantOperator.class.getName()); 
	
	@Override
	public boolean opcodeMatch(String opcode) 
	{
		try
		{
			Double.parseDouble(opcode);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
	{		
		if (stack == null || token == null)
			throw new IllegalArgumentException();
		
		try
		{
			double value = Double.parseDouble(token);
			stack.push(value);
		}
		catch (NumberFormatException e)
		{			
			String message = String.format("%s is not a numeric constant", token);
			
			LOGGER.severe(message);
			throw new FormulaEvaluatorException(message);
		}
	}
}
