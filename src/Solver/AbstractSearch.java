package Solver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Common.Node;
import Common.SolveData;

public abstract class AbstractSearch implements ISearch {
	
	public List<Point> solve(SolveData solveData) {
		Node endNode = innerSolve(solveData);
		
		List<Point> path = new ArrayList<Point>();
        while (endNode != null) {
        	path.add(new Point(endNode.x, endNode.y));
        	endNode = endNode.parent != null? endNode.parent : null;
        }
        return path;
	}
	
	protected abstract Node innerSolve(SolveData solveData);
	
	
}
