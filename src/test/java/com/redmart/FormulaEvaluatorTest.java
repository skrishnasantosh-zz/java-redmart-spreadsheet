package com.redmart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.redmart.FormulaEvaluator;
import com.redmart.exceptions.FormulaEvaluatorException;

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
		FormulaEvaluator evaluator = new FormulaEvaluator(new String[] { "3", "2", "1", "+" });
		
		//assert and act
		assertThrows(FormulaEvaluatorException.class, () -> {
			evaluator.evaluate();
		});
	}
	
	@Test	
	public void testEvaluate_lessOperands_throwsFormulaEvaluationException()
	{
		//arrange
		FormulaEvaluator evaluator = new FormulaEvaluator(new String[] { "1", "+" });
		
		//assert and act
		assertThrows(FormulaEvaluatorException.class, () -> {
			evaluator.evaluate();
		});
	}
	
	@Test
	public void testEvaluate_whenValidInputsAreGiven_shouldCalculate() throws FormulaEvaluatorException
	{
		//arrange
		String[] rpnFormula = "15 7 1 1 + - / 3 * 2 1 1 + + -".split(" ");
		FormulaEvaluator evaluator = new FormulaEvaluator(rpnFormula);
		
		//assert and act
		assertEquals(5, evaluator.evaluate());
	}
	
}
