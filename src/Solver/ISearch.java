package Solver;

import java.awt.Point;
import java.util.List;

import Common.SolveData;

public interface ISearch {
	public List<Point> solve(SolveData solveData); 
}
