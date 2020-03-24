package com.redmart;

public class InvalidInputException extends Exception
{
	private static final long serialVersionUID = 2191233974867239926L;

	public InvalidInputException(String message)
	{
		super(message);
	}
}
