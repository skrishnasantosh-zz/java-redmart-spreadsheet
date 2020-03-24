package com.redmart.operators;

import java.util.Stack;

import com.redmart.exceptions.FormulaEvaluatorException;

public class GenericArithmeticOperator implements IOperator 
{       
    @Override
    public boolean opcodeMatch(String opcode) 
    {
        ArithmeticOperator operator = ArithmeticOperator.getByOpcode(opcode);
        return operator != null;
    }

    @Override
    public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
    {
        ArithmeticOperator operator = ArithmeticOperator.getByOpcode(token);
        double value = operator.calculate(stack);

        stack.push(value);
    }
}
 