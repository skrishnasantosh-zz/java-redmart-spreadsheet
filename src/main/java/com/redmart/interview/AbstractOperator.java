package main.java.com.redmart.interview;

import java.util.logging.Logger;
import java.util.regex.*;

public abstract class AbstractOperator implements IOperator 
{
	protected Logger logger;
	private String opCode;
	private int expectedOpcodeLength;
	
	protected AbstractOperator(String opcode, int expectedOpcodeLength)
	{
		opCode = opcode;
		this.expectedOpcodeLength = expectedOpcodeLength;	
	}
	
	protected AbstractOperator(String opcode)
	{
		this(opcode, 1);	
	}
	
	public boolean opcodeMatch(String opcode) 
	{ 
		opcode = opcode.trim();
		
		return opcode == opCode && opcode.length() == this.expectedOpcodeLength;
	}
	
	public AbstractOperator()
	{
		logger = Logger.getLogger(this.getClass().getName());
		logger.setUseParentHandlers(false);
	}
}
