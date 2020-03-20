package main.java.com.redmart.interview;

import java.util.Stack;

public class NopOperator implements IOperator 
{

	@Override
	public boolean opcodeMatch(String opcode) 
	{
		return opcode == null || opcode.trim().isEmpty();
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
	{
		// do nothing
	}

}
