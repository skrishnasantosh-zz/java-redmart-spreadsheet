package main.java.com.redmart.interview;

import java.util.logging.Logger;
import java.io.PrintStream;
import java.util.logging.Level;

public class Worksheet {
	
	private static final Logger LOGGER = Logger.getLogger(Worksheet.class.getName());
	
	private CellNode cells[][];		
	private int height, width;
		
	public Worksheet(int height, int width)
	{	
		LOGGER.setUseParentHandlers(false);
		
		if (height <= 0 || width <= 0)
			throw new IllegalArgumentException();						
		
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
					cells[rowIdx][colIdx] = new CellNode(String.format("%s%d", firstChar, (colIdx + 1)), rowIdx, colIdx);
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
		
	public void dumpTo(PrintStream stream)
	{
		stream.println(String.format("%d %d", width, height));
		
		for (int h = 0; h < this.height; h++)
		{
			for (int w = 0; w < this.width; w++)
			{
				CellNode cell = cells[h][w];
				Double value = cell.getValue();
				
				if (cell != null && value != null)
				{
					stream.println(String.format("%.5f", value));
				}
			}
		}
	}	
	
	
	public void setCellFormula(String cellId, String formula) throws InvalidCellReferenceException, FormulaEvaluatorException, CyclicDependencyException
	{
        if (formula == null || formula.isEmpty())
        	throw new IllegalArgumentException(formula);
        
        CellNode cell = getCell(cellId);

        // Cell value will not be null when it gets here        
        String[] tokens = formula.split(" ");
        
        FormulaEvaluator evaluator = new FormulaEvaluator(tokens);
        
        cell.setFormula(tokens);
        
        if (evaluator.hasCellReference())
        {
        	 cell.setValue(null);
        	 createGraphEdges(cell, tokens);        	 
        }
        else        	
        {        	
        	Double value = evaluator.evaluate();        	
        	cell.setValue(value);        	
        }
	}
	
	private void createGraphEdges(CellNode cell, String[] formula) throws InvalidCellReferenceException, CyclicDependencyException, FormulaEvaluatorException
	{
		for (String token : formula)
		{
			if (Character.isLetter(token.charAt(0))) 
			{
				CellNode edge = getCell(token);				
				cell.addEdge(edge);
				
				checkCycles(cell, null);
				
				edge.getObserver().add(cell);
				edge.getObserver().sendNotify(cell);
			}
		}
	}
	
	private void checkCycles(CellNode source, CellNode recurse) throws CyclicDependencyException
	{
		if (recurse == null)
			recurse = source;
		
		Iterable<CellNode> edges = recurse.getEdges();
		
		for (CellNode node : edges)
		{
			if (node.getName().equalsIgnoreCase(source.getName()))
				throw new CyclicDependencyException(source.getName());
			
			checkCycles(source, node);
		}
	}
}
