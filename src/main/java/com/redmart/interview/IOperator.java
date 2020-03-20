package main.java.com.redmart.interview;

import java.util.Stack;

public interface IOperator
{
	String getOpcode();
	void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException;
}
