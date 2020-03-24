package com.redmart;

import java.util.Stack;

import com.redmart.IOperator;

public class CellReferenceOperator implements IOperator 
{
	@Override
	public boolean opcodeMatch(String opcode) 
	{
		return  Character.isLetter(opcode.trim().charAt(0)) && 
				opcode.trim().length() > 1 &&
				Character.isDigit(opcode.trim().charAt(1));
	}

	@Override
	public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
	{		
		// when it reaches here, the reference is a cell whose value has not been specified. 
		// So lets assume that value to be zero
		
		stack.push(0.0);
	}
}
