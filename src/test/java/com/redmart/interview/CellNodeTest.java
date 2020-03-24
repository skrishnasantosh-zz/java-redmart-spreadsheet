package test.java.com.redmart.interview;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import main.java.com.redmart.interview.Worksheet;
import main.java.com.redmart.interview.CellNode;
import main.java.com.redmart.interview.FormulaEvaluatorException;

public class CellNodeTest 
{
	@Test
	public void testCellNode_setCellValueAsNull_shouldSetCellValueAsNull() throws FormulaEvaluatorException
	{
		// arrange		
		Worksheet sheet = new Worksheet(2, 3);
		CellNode node = sheet.getCell("A1");
		
		// act		
		node.setValue(null);
		
		// assert		
		assertNull(node.getValue());
	}
}
