package dotandboxes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AI {
	int maxDepth;
	int[] optimalTurn;
	SucessorFunction sucessorFunction;
	
	public AI() {
		this.sucessorFunction = new SucessorFunction();
		this.maxDepth = 5;
	}
	
	public void buildTree(Node start) {
		
		Queue<Node> queue = new LinkedList<Node>(); 
		Node current = start;
		
		
		
		while(current.getDepth() != maxDepth) {
		ArrayList<Node> sucessors = sucessorFunction.getSucessors(current);
		System.out.println(sucessors.size());
		if(sucessors.size() == 0) {
			break;
		}
		
		for (Node successor : sucessors) {
			current.addChild(successor);
			queue.add(successor);
		}
		
		current = queue.poll();
		}
		
		
	}

}
