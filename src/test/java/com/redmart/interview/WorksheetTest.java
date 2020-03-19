package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.InvalidCellReferenceException;
import main.java.com.redmart.interview.Workbook;
import main.java.com.redmart.interview.Worksheet;

class WorksheetTest {

	@Test
	void testWorksheet_invalidArgumentsToConstructor_throwsIllegalArgumentException() 
	{	
		
		Workbook book = new Workbook();
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(book, 0, 20);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(book, 20, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(null, 20, 20);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(null, 0, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(null, -1, -1);
		});
	}
	
	@Test
	void testWorksheet_getHeightAndWidthCorrectly_createsCellsAndReturnsHeightAndWidth() 
	{	
		
		Workbook book = new Workbook();
		
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		assertEquals(2, sheet.getHeight());
		assertEquals(3, sheet.getWidth());
		
		assertEquals(2, sheet.getCells().length);
		assertEquals(3, sheet.getCells()[0].length);		
	}
	
	@Test
	void testWorksheet_getCellAtWithInvalidBounds_throwsIllegalArgumentException()
	{	
		
		Workbook book = new Workbook();		
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			sheet.getCellAt(2, 2);	
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			sheet.getCellAt(2, 3);	
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			sheet.getCellAt(103, 1003);	
		});
	}

	@Test
	void testWorksheet_getCellFromStringIdWithValidId_returnsAppropriateCell() throws InvalidCellReferenceException 
	{	
		
		Workbook book = new Workbook();
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		assertNotNull(sheet.getCell("A1"));
		assertNotNull(sheet.getCell("B2"));
		assertNotNull(sheet.getCell("B3"));
		assertNotNull(sheet.getCell("A3"));
	}
	
	@Test
	void testWorksheet_getCellFromStringIdWithInvalidId_throwsInvalidCellReferenceException()
	{		
		Workbook book = new Workbook();
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		assertThrows(InvalidCellReferenceException.class, () -> {
			sheet.getCell("C1");
		});
		assertThrows(InvalidCellReferenceException.class, () -> {
			sheet.getCell("A5");
		});
		assertThrows(InvalidCellReferenceException.class, () -> {
			sheet.getCell("AA");
		});
		assertThrows(InvalidCellReferenceException.class, () -> {
			sheet.getCell("12");
		});		
	}
}
