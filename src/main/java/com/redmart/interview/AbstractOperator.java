package main.java.com.redmart.interview;

import java.util.logging.Logger;

public abstract class AbstractOperator implements IOperator 
{
	protected Logger logger;
	private String opCode;
	
	public AbstractOperator(String opcode)
	{
		opCode = opcode;
	}
	
	public String getOpcode() 
	{ 
		return opCode;
	}
	
	public AbstractOperator()
	{
		logger = Logger.getLogger(this.getClass().getName());
		logger.setUseParentHandlers(false);
	}
}
