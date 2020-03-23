package main.java.com.redmart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import main.java.com.redmart.interview.operators.AdditionOperator;
import main.java.com.redmart.interview.operators.CellReferenceOperator;
import main.java.com.redmart.interview.operators.DecrementOperator;
import main.java.com.redmart.interview.operators.DivisionOperator;
import main.java.com.redmart.interview.operators.IncrementOperator;
import main.java.com.redmart.interview.operators.MultiplicationOperator;
import main.java.com.redmart.interview.operators.NopOperator;
import main.java.com.redmart.interview.operators.NumericConstantOperator;
import main.java.com.redmart.interview.operators.SubtractionOperator;

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
		operators.add(new AdditionOperator());
		operators.add(new SubtractionOperator());
		operators.add(new MultiplicationOperator());
		operators.add(new DivisionOperator());
		operators.add(new IncrementOperator());
		operators.add(new DecrementOperator());		
		operators.add(new NumericConstantOperator());
		operators.add(new NopOperator());		
	}
	
	public IOperator getOperator(String token) throws OperatorNotFoundException
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
				throw new OperatorNotFoundException(message);
			 }
			 
			 return operator;
		}
		catch (NoSuchElementException e)
		{
			throw new OperatorNotFoundException(token);
		}		
	}	
}
