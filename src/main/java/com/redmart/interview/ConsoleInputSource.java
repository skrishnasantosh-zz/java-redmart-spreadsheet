package main.java.com.redmart.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputSource implements IInputSource {
	private BufferedReader consoleReader;
	
	public ConsoleInputSource()
	{
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Override
	public String[] readLine() throws IOException {
		
		String line = consoleReader.readLine();
		
		if (line == null)
			return null;
		
		return line.split(" ");
	}

}
