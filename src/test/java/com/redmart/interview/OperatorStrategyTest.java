package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.AdditionOperator;
import main.java.com.redmart.interview.IOperator;
import main.java.com.redmart.interview.OperatorNotFoundException;
import main.java.com.redmart.interview.OperatorStrategy;

public class OperatorStrategyTest 
{
	@Test
	public void testGetOperator_whenInvalidOperatorIsProvided_throwsOperatorNotFoundException()
	{
		OperatorStrategy strategy = new OperatorStrategy();
		
		assertThrows(OperatorNotFoundException.class, () -> {
			strategy.getOperator("o_o");
		});	
	}
	
	@Test
	public void testGetOperator_whenTokenIsProvided_getsMatchingOperator() throws OperatorNotFoundException
	{
		OperatorStrategy strategy = new OperatorStrategy();
		
		IOperator operator = strategy.getOperator("+");
		
		assertNotNull(operator);
		assertEquals(operator.getClass(), AdditionOperator.class);		
	}
}
