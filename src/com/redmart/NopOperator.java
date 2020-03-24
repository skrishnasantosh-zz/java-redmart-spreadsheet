package com.redmart;

import java.util.Stack;

import com.redmart.IOperator;

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
