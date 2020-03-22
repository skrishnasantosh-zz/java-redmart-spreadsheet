package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.*;

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
		IOperator operator1 = strategy.getOperator("+");
		String className = operator1.getClass().getName();
		
		assertNotNull(operator1);
		assertEquals(AdditionOperator.class.getName(), className);		
		
		IOperator operator2 = strategy.getOperator("-");
		
		assertNotNull(operator2);
		assertEquals(SubtractionOperator.class.getName(), className);
		
		IOperator operator3 = strategy.getOperator("*");
		
		assertNotNull(operator3);
		assertEquals(MultiplicationOperator.class.getName(), className);
		
		IOperator operator4 = strategy.getOperator("/");
		
		assertNotNull(operator4);
		assertEquals(DivisionOperator.class.getName(), className);
		
		IOperator operator5 = strategy.getOperator("++");
		
		assertNotNull(operator5);
		assertEquals(IncrementOperator.class.getName(), className);
		
		IOperator operator6 = strategy.getOperator("--");
		
		assertNotNull(operator6);
		assertEquals(DecrementOperator.class.getName(), className);
		
		IOperator operator7 = strategy.getOperator("");
		
		assertNotNull(operator7);
		assertEquals(NopOperator.class.getName(), className);
		
		IOperator operator8 = strategy.getOperator("1");
		
		assertNotNull(operator8);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		IOperator operator9 = strategy.getOperator("1.1992");
		
		assertNotNull(operator9);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		IOperator operator10 = strategy.getOperator("-1.1992");
		
		assertNotNull(operator10);
		assertEquals(NumericConstantOperator.class.getName(), className);
		
		IOperator operator11 = strategy.getOperator("A1");
		
		assertNotNull(operator11);
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
