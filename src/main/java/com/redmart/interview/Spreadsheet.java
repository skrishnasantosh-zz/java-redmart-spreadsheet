package main.java.com.redmart.interview;

import java.util.logging.Handler;
import java.util.logging.Logger;

public class Spreadsheet 
{	
	public static void main(String[] args) 
	{		
		IInputSource inSource = new ConsoleInputSource();
		IOutputSource outSource = new ConsoleOutputSource();
		
		Workbook book = new Workbook();
		Worksheet sheet = book.createWorksheet(inSource);
		
		sheet.dumpTo(outSource);
	}	
}
