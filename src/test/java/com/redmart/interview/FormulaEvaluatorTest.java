package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.FormulaEvaluator;
import main.java.com.redmart.interview.FormulaEvaluatorException;

public class FormulaEvaluatorTest 
{
	@Test
	public void testHasCellReference_whenCellReferenceIsFound_shouldReturnTrue()
	{
		//arrange
		FormulaEvaluator evaluator = new FormulaEvaluator(new String[] { "4", "A1", "+" });
		
		//assert and act
		assertTrue(evaluator.hasCellReference());
	}
	
	@Test
	public void testEvaluate_imbalancedFormula_throwsFormulaEvaluationException()
	{
		//arrange
		FormulaEvaluator evaluator = new FormulaEvaluator(new String[] { "A1", "+" });
		
		//assert and act
		assertThrows(FormulaEvaluatorException.class, () -> {
			evaluator.evaluate();
		});
	}
}
