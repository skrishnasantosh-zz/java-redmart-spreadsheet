package main.java.com.redmart.interview;

public class Spreadsheet {

	public static void main(String[] args) {
		
		IInputSource inSource = new ConsoleInputSource();
		IOutputSource outSource = new ConsoleOutputSource();
		
		Workbook book = new Workbook();
		Worksheet sheet = book.createWorksheet(inSource);
		
		sheet.dumpTo(outSource);
	}

}
