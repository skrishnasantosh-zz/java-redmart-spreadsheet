package main.java.com.redmart.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Spreadsheet 
{	
	public static void main(String[] args) 
	{		
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));		
		String source = consoleReader.lines().collect(Collectors.joining());
		
		try
		{
			Workbook book = new Workbook();
			Worksheet sheet = book.createWorksheet(source);

			sheet.dumpTo(System.out);
		}
		catch (InvalidInputException e)
		{
			e.printStackTrace();
		} catch (InvalidCellReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormulaEvaluatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CyclicDependencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
