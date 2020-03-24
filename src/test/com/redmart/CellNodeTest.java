package test.com.redmart;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.redmart.CellNode;
import com.redmart.FormulaEvaluatorException;
import com.redmart.Worksheet;

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
