package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.FormulaEvaluatorException;
import main.java.com.redmart.interview.IOperator;
import main.java.com.redmart.interview.OperatorNotFoundException;
import main.java.com.redmart.interview.OperatorStrategy;

public class OperatorTest 
{
	@Test
	public void testAdditionOperator_whenValidInputsAreGiven_shouldCalculateAddition() throws FormulaEvaluatorException
	{		
		double value = calculateWithTwoOperands("+", 10, 20);
		
		assertEquals(30, value);
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
	
	private IOperator getOperator(String opcode) throws OperatorNotFoundException
	{
		OperatorStrategy strategy = new OperatorStrategy();
		IOperator operator = strategy.getOperator(opcode);
		
		return operator;
	}
	
	private Stack<Double> getTwoOperands(double lValue, double rValue)
	{
		Stack<Double> stack = new Stack<Double>();
		stack.add(rValue);
		stack.add(lValue);		
		
		return stack;
	}
	
	private Stack<Double> getSingleOperand(double lValue, double rValue)
	{
		Stack<Double> stack = new Stack<Double>();
		stack.add(rValue);
		stack.add(lValue);		
		
		return stack;
	}
}
