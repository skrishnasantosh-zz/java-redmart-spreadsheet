package main.java.com.redmart.interview;

import java.util.Stack;

public class GenericArithmeticOperator implements IOperator 
{       
    @Override
    public boolean opcodeMatch(String opcode) 
    {
        
        System.out.println("SEARCHING FOR " + opcode);

        ArithmeticOperator operator = ArithmeticOperator.INSTANCE.getByOpcode(opcode);

        System.out.println(" FOUND = " + operator != null);

        return operator != null;
    }

    @Override
    public void operate(String token, Stack<Double> stack) throws FormulaEvaluatorException 
    {
        ArithmeticOperator operator = ArithmeticOperator.INSTANCE.getByOpcode(token);

        System.out.println(" INVOKING CALCULATE " + token);
        double value = operator.calculate(stack);

        stack.push(value);
    }
}
 