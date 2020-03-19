package main.java.com.redmart.interview;

public class Worksheet {
	
	private CellNode cells[][];
	private Workbook parent;
	
	private int height, width;
	
	public Worksheet(Workbook book, int height, int width)
	{
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
	
	public CellNode getCell(String cellId)
	{
		return null;		
	}	
	
	public void dumpTo(IOutputSource outSource)
	{
		
	}
}
