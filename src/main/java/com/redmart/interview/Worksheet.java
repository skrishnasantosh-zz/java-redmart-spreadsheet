package main.java.com.redmart.interview;

import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Worksheet {
	
	private static final Logger LOGGER = Logger.getLogger(Worksheet.class.getName());
	
	private CellNode cells[][];
	private Workbook parent;	
	private int height, width;    
	
	public Worksheet(Workbook book, int height, int width)
	{	
		LOGGER.setUseParentHandlers(false);
		
		if (height <= 0 || width <= 0 || book == null)
			throw new IllegalArgumentException();
						
		this.parent = book;
		cells = new CellNode[height][width];
		
		this.height = height;
		this.width = width;
	}
	
	public int getHeight()
	{
		return this.height; 
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public CellNode[][] getCells()
	{
		return cells;
	}
	
	public CellNode getCellAt(int row, int col)
	{
		if (row <= 0 || row >= height ||
			col <= 0 || col >= width)
			throw new IllegalArgumentException();
		
		return cells[row][col];
	}
	
	public CellNode getCell(String cellId) throws InvalidCellReferenceException
	{
		CellNode node = null;
		
		if (cellId == null || cellId.trim().length() <= 1)
			throw new IllegalArgumentException();
		
		cellId = cellId.trim();
		char firstChar = cellId.toUpperCase().charAt(0);
				
		if (Character.isLetter(firstChar))
		{
			String colRefStr = cellId.substring(1);
			
			try 
			{   
				int colIdx = Integer.parseInt(colRefStr) - 1; // zero based index
				int rowIdx = (int)(firstChar - 'A'); // zero based index
				
				if (rowIdx < 0 || colIdx < 0 || 
					rowIdx >= height || colIdx >= width)
				{
					String message = String.format("%s - row or col is outside the bounds of worksheet", colRefStr);
					
					LOGGER.severe(message);					
					throw new InvalidCellReferenceException(message);					
				}
				
				node = cells[rowIdx][colIdx];
				
				if (node == null)  // create the cell if it is not yet initialized as this cell is being referenced
				{
					cells[rowIdx][colIdx] = new CellNode(this, String.format("%s%d", firstChar, (colIdx + 1)), rowIdx, colIdx);
					node = cells[rowIdx][colIdx];
				}				
			}
			catch (NumberFormatException e)	
			{
				String message = String.format("invalid column reference %s", colRefStr);
				
				LOGGER.log(Level.SEVERE, message, e);				
				throw new InvalidCellReferenceException(message);
			}
		}
		else
		{
			String message = String.format("invalid cell id %s", cellId);
						
			LOGGER.severe(message);
			throw new InvalidCellReferenceException(message);
		}
		
		return node;
	}	
	
	public void dumpTo(IOutputSource outSource)
	{
		
	}	
}
