package test.com.redmart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.redmart.CellReferenceOperator;
import com.redmart.FormulaEvaluatorException;
import com.redmart.GenericArithmeticOperator;
import com.redmart.IOperator;
import com.redmart.NumericConstantOperator;
import com.redmart.OperatorStrategy;

public class OperatorStrategyTest 
{
	@Test
	public void testGetOperator_whenInvalidOperatorIsProvided_throwsOperatorNotFoundException()
	{
		// arrange
		OperatorStrategy strategy = new OperatorStrategy();
		
		// act and assert
		assertThrows(FormulaEvaluatorException.class, () -> {
			strategy.getOperator("o_o");
		});	
	}
	
	@Test
	public void testGetOperator_whenTokenIsProvided_getsMatchingOperator() throws FormulaEvaluatorException
	{
		// arrange
		OperatorStrategy strategy = new OperatorStrategy();
		
		
		// act and assert
		IOperator operator = strategy.getOperator("+");
		String className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);		
		
		operator = strategy.getOperator("-");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);
		
		operator = strategy.getOperator("*");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);
		
		operator = strategy.getOperator("/");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);
		
		operator = strategy.getOperator("++");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);
		
		operator = strategy.getOperator("--");
		className = operator.getClass().getName();
		
		assertNotNull(operator);
		assertEquals(GenericArithmeticOperator.class.getName(), className);
		
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
	public void testGetOperator_whenCellReferenceIsProvided_getsCellReferenceOperator() throws FormulaEvaluatorException
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
