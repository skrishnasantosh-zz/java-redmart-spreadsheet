package com.redmart;

import java.util.ArrayList;
import java.util.List;

import com.redmart.exceptions.FormulaEvaluatorException;

public class CellNodeObserver 
{
	private List<CellNode> observers; 
	private CellNode parent;
	
	public CellNodeObserver(CellNode parent)
	{
		this.parent = parent;
		observers = new ArrayList<CellNode>();
	}
	
	public void add(CellNode node)
	{
		observers.add(node);
	}
	
	public Iterable<CellNode> getAll()
	{
		return observers;
	}
	
	public void sendNotify() throws FormulaEvaluatorException
	{
		for (CellNode observerCell : observers)
		{
			sendNotify(observerCell);
		}
	}
	
	public void sendNotify(CellNode node) throws FormulaEvaluatorException
	{
		node.onNotify(parent);
	}
}
