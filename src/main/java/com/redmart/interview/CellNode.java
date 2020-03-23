package main.java.com.redmart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CellNode
{
	private Double value;
	private List<String> formula;
	private String[] resolvedFormula;
	
	private List<CellNode> edges;
	
	private String name;	
	
	private boolean isReady;
	
	private CellNodeObserver observer;
	
	public CellNode(String id, int row, int col)
	{		
		name = id;		
		edges = new ArrayList<CellNode>();
		
		observer = new CellNodeObserver(this);
	}
		
	public String getName()
	{
		return name;
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
	
	public void addEdge(CellNode cell)
	{
		edges.add(cell);
	}	
	
	public CellNodeObserver getObserver()
	{
		return observer;
	}
	
	public void onNotify(CellNode updatedNode) throws FormulaEvaluatorException
	{
		for (int i = 0; i < this.getFormulaCount(); i ++)
		{
			if (formula.get(i).equalsIgnoreCase(updatedNode.getName()) &&
				updatedNode.getValue() != null)
			{
				resolvedFormula[i] = updatedNode.getValue().toString();
				break;
			}
		}
		
		evaluate();
	}
	
	public Double getValue()
	{
		return value;
	}

	public void setValue(Double value) throws FormulaEvaluatorException
	{
		this.value = value;
		observer.sendNotify();
	}
	
	public String getFormulaAsString()
	{
		return String.join(" ", formula);
	}
	
	public Iterable<String> getFormula()
	{
		return formula;
	}
	
	public void setFormula(String[] formula)
	{
		this.formula = new ArrayList<String>();
		this.resolvedFormula = new String[formula.length];
		
		for(String token : formula)
		{
			this.formula.add(token);			
		}
		
		this.resolvedFormula = formula.clone();
	}	
		
	public boolean isReady()
	{
		return isReady;
	}
	
	public void evaluate() throws FormulaEvaluatorException
	{
		FormulaEvaluator evaluator = new FormulaEvaluator(resolvedFormula);
		
		if (evaluator.hasCellReference())
			return;
		
		Double result = evaluator.evaluate();
		setValue(result);
	}
}
