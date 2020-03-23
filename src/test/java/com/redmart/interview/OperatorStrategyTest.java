package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.*;
import main.java.com.redmart.interview.operators.AdditionOperator;
import main.java.com.redmart.interview.operators.CellReferenceOperator;
import main.java.com.redmart.interview.operators.DecrementOperator;
import main.java.com.redmart.interview.operators.DivisionOperator;
import main.java.com.redmart.interview.operators.IncrementOperator;
import main.java.com.redmart.interview.operators.MultiplicationOperator;
import main.java.com.redmart.interview.operators.NumericConstantOperator;
import main.java.com.redmart.interview.operators.SubtractionOperator;

public class OperatorStrategyTest 
{
	@Test
	public void testGetOperator_whenInvalidOperatorIsProvided_throwsOperatorNotFoundException()
	{
		// arrange
		OperatorStrategy strategy = new OperatorStrategy();
		
		// act and assert
		assertThrows(OperatorNotFoundException.class, () -> {
			strategy.getOperator("o_o");
		});	
	}
	
	@Test
	public void testGetOperator_whenTokenIsProvided_getsMatchingOperator() throws OperatorNotFoundException
	{
		// arrange
		OperatorStrategy strategy = new OperatorStrategy();
		
		
		// act and assert
		IOperator operator = strategy.getOperator("+");
		String className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(AdditionOperator.class.getName(), className);		
		
		operator = strategy.getOperator("-");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(SubtractionOperator.class.getName(), className);
		
		operator = strategy.getOperator("*");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(MultiplicationOperator.class.getName(), className);
		
		operator = strategy.getOperator("/");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(DivisionOperator.class.getName(), className);
		
		operator = strategy.getOperator("++");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(IncrementOperator.class.getName(), className);
		
		operator = strategy.getOperator("--");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(DecrementOperator.class.getName(), className);
		
		operator = strategy.getOperator("");			
		assertNull(operator);
		
		operator = strategy.getOperator("1");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		operator = strategy.getOperator("1.1992");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		operator = strategy.getOperator("-1.1992");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		operator = strategy.getOperator("A1");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(CellReferenceOperator.class.getName(), className);
	}
	
	@Test
	public void testGetOperator_whenCellReferenceIsProvided_getsCellReferenceOperator() throws OperatorNotFoundException
	{
		// arrange
		OperatorStrategy strategy = new OperatorStrategy();
		
		// act
		IOperator operator = strategy.getOperator("A1");
		
		// assert
		assertNotNull(operator);
		assertEquals(CellReferenceOperator.class, operator.getClass());		
	}	
}
