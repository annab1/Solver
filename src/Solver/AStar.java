package Solver;
import java.util.PriorityQueue;

import Common.Node;
import Common.SolveData;
import Common.SuccessorLogic;

import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;

public class AStar extends AbstractSearch{
           
		@Override
        public Node innerSolve(SolveData solveData) {
            final PriorityQueue<Node> pQueue = new PriorityQueue<Node>(10, new Comparator<Node>() {
                public int compare(Node node1, Node node2) {
                	return (int) (node1.priority - node2.priority);
                }
            });
            
            Node startNode = solveData.domain.getInitialNode(solveData.start);
            final Map<Node, Double> costSoFar = new HashMap<Node, Double>();
            costSoFar.put(startNode, 0.0);
            
            Node current = startNode;
            pQueue.add(current);
            int stepsCounter = -1;
            while (pQueue.size() != 0) {

                current = pQueue.poll();
                stepsCounter++;
                
                boolean reachedStepsAmount = solveData.steps > 0 && stepsCounter == solveData.steps;
 
                if (reachedStepsAmount || solveData.domain.isTarget(current)) {
                	return current;
                }
                
                final Node currentCopy = current;
                
                solveData.domain.foreachSuccessor(current, new SuccessorLogic() {
                	@Override
                	public void checkSuccessor(Node successor, double distanceToEnd) {
                		double newCost = costSoFar.get(currentCopy) + 1;
                		
                		if((!costSoFar.containsKey(successor) || newCost < costSoFar.get(successor))) {
                    		costSoFar.put(successor, newCost);
                    		successor.priority = newCost + distanceToEnd;
                    		successor.parent = currentCopy;
	                        pQueue.add(successor);
                    	}
                	}
                });
            }
            
            return null;
        }
      
}