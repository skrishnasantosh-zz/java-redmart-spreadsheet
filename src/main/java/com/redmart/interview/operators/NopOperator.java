package main.java.com.redmart.interview.operators;

import java.util.Stack;

import main.java.com.redmart.interview.FormulaEvaluatorException;
import main.java.com.redmart.interview.IOperator;

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
