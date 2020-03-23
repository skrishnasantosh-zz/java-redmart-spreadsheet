package main.java.com.redmart.interview;

import java.util.Scanner;

public class Workbook {
		
	public Worksheet createWorksheet(String source) throws InvalidInputException, InvalidCellReferenceException, FormulaEvaluatorException, CyclicDependencyException
	{
		//The first line is assumed to contain the dimensions w x h, parse it 
		Scanner scanner = new Scanner(source);
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
		
		Worksheet sheet = new Worksheet(height, width);
						
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
}
