package com.redmart;

import java.util.Stack;

import com.redmart.exceptions.FormulaEvaluatorException;


public interface IOperator
{
	boolean opcodeMatch(String opcode);
	void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException;
}
