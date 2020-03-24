package com.redmart;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import com.redmart.exceptions.FormulaEvaluatorException;
import com.redmart.operators.CellReferenceOperator;
import com.redmart.operators.GenericArithmeticOperator;
import com.redmart.operators.IOperator;
import com.redmart.operators.NopOperator;
import com.redmart.operators.NumericConstantOperator;

public class OperatorStrategy 
{
	private List<IOperator> operators;
	private static final Logger LOGGER = Logger.getLogger(OperatorStrategy.class.getName());
	
	public OperatorStrategy()
	{
		operators = new ArrayList<IOperator>();
		
		//Did not use reflection as it makes it a bit complicated
		//and no DI libraries as per requirement. 
		//So manually add the classes
		
		operators.add(new CellReferenceOperator());		
		operators.add(new NumericConstantOperator());
		operators.add(new NopOperator());		
		operators.add(new GenericArithmeticOperator());
	}
	
	public IOperator getOperator(String token) throws FormulaEvaluatorException
	{
		try 
		{	
			if (token == null || token.trim().isEmpty())
				return null;
			
			 IOperator operator = operators.stream()
		 								   .filter((o) -> o.opcodeMatch(token))
		 								   .findAny()
		 								   .orElse(null);

			 if (operator == null)
			 {
				String message = String.format("operator %s not found", token);
								
				LOGGER.severe(message);
				throw new FormulaEvaluatorException(message);
			 }
			 
			 return operator;
		}
		catch (NoSuchElementException e)
		{
			throw new FormulaEvaluatorException(token);
		}		
	}	
}
