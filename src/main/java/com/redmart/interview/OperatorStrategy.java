package main.java.com.redmart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OperatorStrategy 
{
	private List<IOperator> operators;
	
	public OperatorStrategy()
	{
		operators = new ArrayList<IOperator>();
		
		//Did not use reflection as it makes it a bit complicated
		//and no DI libraries as per requirement. 
		//So manually add the classes
		
		operators.add(new AdditionOperator());
	}
	
	public IOperator getOperator(String token) throws OperatorNotFoundException
	{
		try 
		{
			 Optional<IOperator> operator = operators.stream()
					 								 .filter((o) -> o.getOpcode().toUpperCase().trim() == 
					 								 				token.toUpperCase().trim())
					 								 .findFirst();
			
			return operator.get();
		}
		catch (NoSuchElementException e)
		{
			throw new OperatorNotFoundException(token);
		}
	}
}
