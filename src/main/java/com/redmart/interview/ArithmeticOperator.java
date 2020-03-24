package main.java.com.redmart.interview;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public enum ArithmeticOperator 
{
    INSTANCE(""),
    ADDITION("+", (a, b) -> a + b), MULTIPLICATION("*", (a, b) -> a * b), DIVISION("/", (a, b) -> a / b),
    SUBTRACTION("-", (a, b) -> a - b), INCREMENT("++", (x) -> ++x), DECREMENT("--", (x) -> --x);

    private HashMap<String, ArithmeticOperator> operators;

    private String opcode;
    private DoubleBinaryOperator binaryOperator;
    private DoubleUnaryOperator unaryOperator;
    private int requiredOperands;

    private ArithmeticOperator(String opcode)
    {
        operators = new HashMap<String, ArithmeticOperator>();        
        this.opcode = opcode;

        operators.put(opcode, this);
    }

    private ArithmeticOperator(String opcode, DoubleBinaryOperator operator) 
    {
        this(opcode);
        
        this.binaryOperator = operator;
        requiredOperands = 2;
    }

    private ArithmeticOperator(String opcode, DoubleUnaryOperator operator) 
    {
        this(opcode);

        this.unaryOperator = operator;
        requiredOperands = 1;
    }

    public String getOpcode() 
    {
        return opcode;
    }

    public ArithmeticOperator getByOpcode(String opcode)
    {
        return operators.getOrDefault(opcode, null);
    }

    public int getRequiredOperandCount()
    {
        return requiredOperands;
    }

    public Double calculate(Stack<Double> values) throws FormulaEvaluatorException
    {
        if (values.size() < requiredOperands)
            throw new FormulaEvaluatorException("insufficient operands for the operator " + opcode);

        if (unaryOperator != null)
        {
            double value = values.pop();
            return unaryOperator.applyAsDouble(value);
        }
        else
        {
            double lValue = values.pop();
            double rValue = values.pop();

            return binaryOperator.applyAsDouble(lValue, rValue);
        }
    }    
}