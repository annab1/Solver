package Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import Common.Node;
import Common.SolveData;
import Common.SuccessorLogic;

public class BestFirstSearch extends AbstractSearch {
	  public Node innerSolve(SolveData solveData) {
	       	
            final PriorityQueue<Node> open = new PriorityQueue<Node>(10, new Comparator<Node>() {
                public int compare(Node node1, Node node2) {
                	return (int) (node1.priority - node2.priority);
                }
            });
            
            final List<Node> closed = new ArrayList<Node>();
            
            Node startNode = solveData.domain.getInitialNode(solveData.start);
            
            Node current = startNode;
            open.add(current);
            int stepsCounter = -1;
            while (open.size() != 0) {

                current = open.poll();
                closed.add(current);
                stepsCounter++;
                
                boolean reachedStepsAmount = solveData.steps > 0 && stepsCounter == solveData.steps;
                if (reachedStepsAmount || solveData.domain.isTarget(current)) {
                	return current;
                }

                final Node currentCopy = current;
                solveData.domain.foreachSuccessor(current, new SuccessorLogic() {

					@Override
					public void checkSuccessor(Node successor, double distanceToEnd) {
						if (!open.contains(successor) && !closed.contains(successor)) {
							successor.priority = distanceToEnd;
							successor.parent = currentCopy;
		                    open.add(successor);
	                    }
					}
                });
                
            }
            
            return null;
	  }

}
