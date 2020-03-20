package main.java.com.redmart.interview;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CellNode 
{
	private Double value;
	private List<String> formula;
	private List<String> resolvedFormula;
	
	private List<CellNode> edges;
	private String name;
	private Position position;
	
	private Worksheet parent;
	
	private FormulaEvaluator formulaEvaluator;
	private boolean isReady;
	
	public CellNode(Worksheet sheet, String id, int row, int col)
	{
		this.parent = sheet;
		name = id;
		position = new Position(row, col);
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getFormulae()
	{
		return formula;
	}
	
	public int getFormulaCount()
	{
		return formula.size();
	}
	
	public Iterable<CellNode> getEdges()
	{
		return edges;
	}
	
	public int getEdgeCount()
	{
		return edges.size();
	}
	
	public Double getValue()
	{
		return value;
	}
	
	public Iterable<String> getResolvedFormulae()
	{
		return resolvedFormula;
	}
	
	public void setValue(Double value)
	{
		this.value = value;
		
		//todo: notify observers
	}
	
	public boolean isReady()
	{
		return isReady;
	}
	
	public void Evaluate()
	{
		CompletableFuture.runAsync(() -> { 
			isReady = false;
			
		}).thenRun(() -> {
			isReady = true;
		});
	}
}
