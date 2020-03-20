package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.InvalidCellReferenceException;
import main.java.com.redmart.interview.Workbook;
import main.java.com.redmart.interview.Worksheet;

class WorksheetTest
{

	@Test
	void testWorksheet_invalidArgumentsToConstructor_throwsIllegalArgumentException() 
	{	
		// arrange
		Workbook book = new Workbook();
		
		// act and assert		
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
		// arrange
		Workbook book = new Workbook();
		
		// act
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		
		// assert
		assertEquals(2, sheet.getHeight());
		assertEquals(3, sheet.getWidth());
		
		assertEquals(2, sheet.getCells().length);
		assertEquals(3, sheet.getCells()[0].length);		
	}
	
	@Test
	void testWorksheet_getCellAtWithInvalidBounds_throwsIllegalArgumentException()
	{	
		// arrange
		Workbook book = new Workbook();		
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		// act and assert		
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
		// arrange		
		Workbook book = new Workbook();
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		// act and assert		
		assertNotNull(sheet.getCell("A1"));
		assertNotNull(sheet.getCell("B2"));
		assertNotNull(sheet.getCell("B3"));
		assertNotNull(sheet.getCell("A3"));
		
		assertEquals("A1", sheet.getCell("A1").getName());
		assertEquals(0, sheet.getCell("A1").getPosition().getRow());
		assertEquals(0, sheet.getCell("A1").getPosition().getCol());
		
		assertEquals("B2", sheet.getCell("B2").getName());
		assertEquals(1, sheet.getCell("B2").getPosition().getRow());
		assertEquals(1, sheet.getCell("B2").getPosition().getCol());
		
		assertEquals("B3", sheet.getCell("b3").getName());
		assertEquals(1, sheet.getCell("b3").getPosition().getRow());
		assertEquals(2, sheet.getCell("b3").getPosition().getCol());
		
		assertEquals("A3", sheet.getCell("A3").getName());
		assertEquals(0, sheet.getCell("A3").getPosition().getRow());
		assertEquals(2, sheet.getCell("A3").getPosition().getCol());

	}
	
	@Test
	void testWorksheet_getCellFromStringIdWithInvalidId_throwsInvalidCellReferenceException()
	{		
		// arrange
		Workbook book = new Workbook();
		Worksheet sheet = new Worksheet(book, 2, 3);
		
		// act and assert
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
