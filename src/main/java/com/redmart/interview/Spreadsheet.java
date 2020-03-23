package main.java.com.redmart.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Spreadsheet 
{		
	public static void main(String[] args) 
	{		
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));		
		String source = consoleReader.lines().collect(Collectors.joining(System.getProperty("line.separator")));		
		
		Workbook book = new Workbook();
		Worksheet sheet = book.createWorksheet(source);

		sheet.dumpTo(System.out);	
	}	
}
