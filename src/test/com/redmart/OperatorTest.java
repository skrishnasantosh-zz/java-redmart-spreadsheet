package test.com.redmart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import com.redmart.FormulaEvaluatorException;
import com.redmart.IOperator;
import com.redmart.OperatorStrategy;

public class OperatorTest 
{
	@Test
	public void testAllOperators_whenValidInputsAreGiven_shouldCalculate() throws FormulaEvaluatorException
	{	
		double value;
		
		value = calculateWithTwoOperands("+", 10, 20);		
		assertEquals(30, value);
		
		value = calculateWithTwoOperands("-", 10, 10);		
		assertEquals(0, value);
		
		value = calculateWithTwoOperands("*", 10, 2);		
		assertEquals(20, value);
		
		value = calculateWithTwoOperands("/", 10, 2);		
		assertEquals(5, value);
		
		value = calculateWithSingleOperand("++", 10);		
		assertEquals(11, value);
		
		value = calculateWithSingleOperand("--", 10);		
		assertEquals(9, value);
	}
	
	@Test
	public void testAllOperators_whenValidNegativeInputsAreGiven_shouldCalculate() throws FormulaEvaluatorException
	{	
		double value;
		
		value = calculateWithTwoOperands("+", -10, 20);		
		assertEquals(10, value);
		
		value = calculateWithTwoOperands("-", -10, 10);		
		assertEquals(-20, value);
		
		value = calculateWithSingleOperand("++", -10);		
		assertEquals(-9, value);
		
		value = calculateWithSingleOperand("--", -10);		
		assertEquals(-11, value);
	}
	
	// Test helpers	
	private double calculateWithTwoOperands(String opcode, double lValue, double rValue) throws FormulaEvaluatorException
	{
		Stack<Double> stack = getTwoOperands(lValue, rValue);
		IOperator operator = getOperator(opcode);
		
		operator.operate(opcode, stack);
		
		double value = stack.pop();
		
		return value;
	}
	
	private double calculateWithSingleOperand(String opcode, double givenValue) throws FormulaEvaluatorException
	{
		Stack<Double> stack = getSingleOperand(givenValue);
		IOperator operator = getOperator(opcode);
		
		operator.operate(opcode, stack);
		
		double value = stack.pop();
		
		return value;
	}
	
	private IOperator getOperator(String opcode) throws FormulaEvaluatorException
	{
		OperatorStrategy strategy = new OperatorStrategy();
		IOperator operator = strategy.getOperator(opcode);
		
		return operator;
	}
	
	private Stack<Double> getTwoOperands(double lValue, double rValue)
	{
		Stack<Double> stack = new Stack<Double>();
		stack.add(lValue);
		stack.add(rValue);		
		
		return stack;
	}
	
	private Stack<Double> getSingleOperand(double value)
	{
		Stack<Double> stack = new Stack<Double>();
		stack.add(value);		
		
		return stack;
	}
}
