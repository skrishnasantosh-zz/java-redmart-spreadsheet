package main.java.com.redmart.interview;

public class Workbook {
		
	public Worksheet createWorksheet(IInputSource inSource)
	{
		Worksheet sheet = new Worksheet(this, 0, 0);
		return sheet;
	}
}
