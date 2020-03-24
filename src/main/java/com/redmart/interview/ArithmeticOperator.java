package main.java.com.redmart.interview;


import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public enum ArithmeticOperator 
{    
    ADDITION("+", (a, b) -> a + b), MULTIPLICATION("*", (a, b) -> a * b), DIVISION("/", (a, b) -> a / b),
    SUBTRACTION("-", (a, b) -> a - b), INCREMENT("++", (x) -> ++x), DECREMENT("--", (x) -> --x);

    private String opcode;
    private DoubleBinaryOperator binaryOperator;
    private DoubleUnaryOperator unaryOperator;
    private int requiredOperands;

    private ArithmeticOperator(String opcode)
    {                
        this.opcode = opcode;
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

    public static ArithmeticOperator getByOpcode(String opcode)
    {
    	for (ArithmeticOperator operator : values())
        {
    		if (operator.opcode.equals(opcode.trim()))
    			return operator;
        }
    	
    	return null;
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
            double rValue = values.pop();
            double lValue = values.pop();

            return binaryOperator.applyAsDouble(lValue, rValue);
        }
    }    
}