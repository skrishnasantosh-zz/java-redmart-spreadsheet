package com.redmart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.redmart.CellNode;
import com.redmart.exceptions.CyclicDependencyException;
import com.redmart.exceptions.FormulaEvaluatorException;
import com.redmart.Worksheet;

class WorksheetTest
{

	@Test
	void testWorksheet_invalidArgumentsToConstructor_throwsIllegalArgumentException() 
	{	
		// arrange, act and assert		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(0, 20);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(20, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(0, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Worksheet(-1, -1);
		});
	}
	
	@Test
	void testWorksheet_getHeightAndWidthCorrectly_createsCellsAndReturnsHeightAndWidth() 
	{	
		// arrange and act
		Worksheet sheet = new Worksheet(2, 3);
		
		
		// assert
		assertEquals(2, sheet.getHeight());
		assertEquals(3, sheet.getWidth());
		
		assertEquals(2, sheet.getCells().length);
		assertEquals(3, sheet.getCells()[0].length);		
	}
	
	@Test
	void testGetCell_atWithInvalidBounds_throwsIllegalArgumentException()
	{	
		// arrange			
		Worksheet sheet = new Worksheet(2, 3);
		
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
	void testGetCell_fromStringIdWithInvalidId_throwsFormulaEvaluationException()
	{		
		// arrange	
		Worksheet sheet = new Worksheet(2, 3);
		
		// act and assert
		assertThrows(FormulaEvaluatorException.class, () -> {
			sheet.getCell("C1");
		});
		assertThrows(FormulaEvaluatorException.class, () -> {
			sheet.getCell("A5");
		});
		assertThrows(FormulaEvaluatorException.class, () -> {
			sheet.getCell("AA");
		});
		assertThrows(FormulaEvaluatorException.class, () -> {
			sheet.getCell("12");
		});		
	}
	
	@Test
	void testSetCellFormula_setNumericExpression_shouldCalculateTheExpressionAndStoreInResult() throws FormulaEvaluatorException, CyclicDependencyException
	{		
		// arrange		
		Worksheet sheet = new Worksheet(2, 3);
		
		// act 
		sheet.setCellFormula("A1", "5 3 *");
		CellNode cell = sheet.getCell("A1");
		
		// assert
		assertEquals(15, cell.getValue());
	}
	
	@Test
	void testSetCellFormula_setCellReference_shouldSetFormulaAsSuchAndCreateEdges() throws FormulaEvaluatorException, CyclicDependencyException
	{		
		// arrange		
		Worksheet sheet = new Worksheet(2, 3);
		
		// act 
		sheet.setCellFormula("A1", "A2 3 *");
		CellNode cell = sheet.getCell("A1");
		
		// assert
		assertEquals("A2 3 *", cell.getFormulaAsString());
	}
	
	@Test
	void testSetCellFormula_setCellReferenceWithOtherCells_shouldSetCorrectValues() throws FormulaEvaluatorException, CyclicDependencyException
	{		
		// arrange		
		Worksheet sheet = new Worksheet(2, 3);
		
		// act 
		sheet.setCellFormula("A1", "A2 ++");
		sheet.setCellFormula("A2", "4 5 *");
		sheet.setCellFormula("A3", "A1 --");
		sheet.setCellFormula("B1", "B2 1 -");
		sheet.setCellFormula("B2", "A1 A2 +");
		sheet.setCellFormula("B3", "B1 2 /");
				
		CellNode cellA1 = sheet.getCell("A1");
		CellNode cellA2 = sheet.getCell("A2");
		CellNode cellA3 = sheet.getCell("A3");
		CellNode cellB1 = sheet.getCell("B1");
		CellNode cellB2 = sheet.getCell("B2");
		CellNode cellB3 = sheet.getCell("B3");
		
		// assert
		assertEquals(21, cellA1.getValue());
		assertEquals(20, cellA2.getValue());
		assertEquals(20, cellA3.getValue());
		assertEquals(40, cellB1.getValue());
		assertEquals(41, cellB2.getValue());
		assertEquals(20, cellB3.getValue());
	}
	
	@Test
	void testSetCellFormula_setCellReferenceWithCycles_shouldThrowCyclicDependencyException() throws FormulaEvaluatorException, CyclicDependencyException
	{
		// arrange		
		Worksheet sheet = new Worksheet(2, 3);
		
		// act 
		sheet.setCellFormula("A1", "A2 3 *");
		
		// act and assert
		assertThrows(CyclicDependencyException.class, () -> { 
			sheet.setCellFormula("A2", "A1 2 *");	
		});
	}
}
