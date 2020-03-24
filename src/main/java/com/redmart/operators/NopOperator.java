package com.redmart.operators;

import java.util.Stack;
import com.redmart.*;

import com.redmart.exceptions.FormulaEvaluatorException;

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
