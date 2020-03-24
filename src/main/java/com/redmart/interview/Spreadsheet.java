package main.java.com.redmart.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Spreadsheet 
{		
	private static final Logger LOGGER = Logger.getLogger(Spreadsheet.class.getName());

	public static void main(String[] args) 
	{		
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));		
		String source = consoleReader.lines().collect(Collectors.joining(System.getProperty("line.separator")));		
		
		Worksheet sheet = createWorksheet(source);

		sheet.dumpTo(System.out);	
	}	
	
	private static Worksheet createWorksheet(String source)
	{
		Worksheet sheet = null;
		Scanner scanner = new Scanner(source);
		
		try 
		{
			//The first line is assumed to contain the dimensions w x h, parse it 			
			if (!scanner.hasNextLine())
				throw new InvalidInputException("");
			
			String line = scanner.nextLine().trim();
			String[] dimensions = line.split(" ");
			
			if (dimensions.length < 2)
				throw new InvalidInputException(source);
			
			int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			
			if (height < 1 || width < 1)
				throw new InvalidInputException(source);
			
			int colIdx = 0;
			char rowPrefix = 'A';
			
			sheet = new Worksheet(height, width);
							
			while (scanner.hasNextLine())
			{
				line = scanner.nextLine().trim();
				String cellId = String.format("%c%d", rowPrefix, colIdx + 1);
				
				sheet.setCellFormula(cellId, line);
				
				colIdx ++;
				if (colIdx >= width)
				{
					rowPrefix ++;
					colIdx = 0;
				}
			}		
			
			return sheet;
		}
		catch (InvalidInputException e)
		{
			String message = "invalid input";
			
			LOGGER.log(Level.SEVERE, message, e);
			System.out.println(message);
			
			System.exit(-1);
			
		} 
		catch (InvalidCellReferenceException e) 
		{
			String message = "invalid cell reference at - " + e.getMessage();
			
			LOGGER.log(Level.SEVERE, message, e);
			System.out.println(message);
			
			System.exit(-2);
		} 
		catch (FormulaEvaluatorException e) 
		{
			String message = e.getMessage();
			
			LOGGER.log(Level.SEVERE, message, e);
			System.out.println(message);
			
			System.exit(-3);
		} 
		catch (CyclicDependencyException e) 
		{
			String message = "cyclic dependency when evaluating - " + e.getMessage();
			
			LOGGER.log(Level.SEVERE, message, e);
			System.out.println(message);
			
			System.exit(-4);
		}
		finally
		{
			scanner.close();;
		}
		
		//Todo : Find out what to do with illegal argument exception and null pointer exception 
		//raised by emptyreference.txt and missingvalues.txt
		
		return sheet;
	}
}
