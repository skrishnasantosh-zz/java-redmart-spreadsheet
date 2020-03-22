package main.java.com.redmart.interview;

import java.util.logging.Logger;

public abstract class AbstractOperator implements IOperator 
{
	protected Logger logger;
	private String opCode;
	private int expectedOpcodeLength;
	
	protected AbstractOperator(String opcode, int expectedOpcodeLength)
	{
		opCode = opcode.trim();
		this.expectedOpcodeLength = expectedOpcodeLength;	
	}
	
	public boolean opcodeMatch(String opcode) 
	{ 
		opcode = opcode.trim();
		
		boolean opEquals = this.opCode.equalsIgnoreCase(opcode);
		int length = opcode.length();
		
		return opEquals && length == this.expectedOpcodeLength;
	}
	
	public AbstractOperator()
	{
		logger = Logger.getLogger(this.getClass().getName());
		logger.setUseParentHandlers(false);
	}
}
