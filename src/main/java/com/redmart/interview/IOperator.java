package main.java.com.redmart.interview;

import java.util.Stack;

public interface IOperator
{
	boolean opcodeMatch(String opcode);
	void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException;
}
