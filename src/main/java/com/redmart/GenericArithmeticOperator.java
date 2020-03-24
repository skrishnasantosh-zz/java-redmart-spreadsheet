package com.redmart;

import java.util.Stack;

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
 