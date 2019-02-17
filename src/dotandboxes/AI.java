package dotandboxes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AI {
	int maxDepth;
	int[] optimalTurn;
	SucessorFunction sucessorFunction;
	Node root;

	public AI() {
		this.sucessorFunction = new SucessorFunction();
		this.maxDepth = 7;
		optimalTurn = new int[2];
		this.root = null;
	}

	public Stack<Node> buildTree(Node start, int numOfMoves) {

		Stack<Node> stack = new Stack<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		Node current = start;

		while (current.getDepth() != maxDepth) {

			ArrayList<Node> sucessors = sucessorFunction.getSucessors(current);

			if (sucessors.size() == 0) {
				break;
			}

			for (Node successor : sucessors) {
				current.addChild(successor);
				queue.add(successor);
			}
			if (numOfMoves == 1) {
				stack.push(current);
			}
			if (numOfMoves < this.maxDepth) {
				if (current.depth < this.maxDepth - 1) {
					stack.push(current);
				}
			} else {
				if (current.depth < numOfMoves - 1) {
					stack.push(current);
				}
			}
			current = queue.poll();
		}
		return stack;

	}

	public int[] findTurn(Stack<Node> tree) {

		Node current = tree.peek();
		int depth = current.getDepth();
		int[] move = new int[2];
		while (!tree.isEmpty()) {
			current = tree.pop();
			int max = current.getChildren().get(0).evalScore;
			int min = current.getChildren().get(0).evalScore;
			for (Node child : current.getChildren()) {
				if (current.isMax()) {
					if (child.evalScore > max) {
						max = child.evalScore;
					}
				} else {
					if (child.evalScore < min) {
						min = child.evalScore;
					}
				}

			}
			if (current.isMax()) {
				current.evalScore = max;
			} else {
				current.evalScore = min;
			}
		
			

		}
		
		root = current;
		System.out.println(current.evalScore);
		for(Node child : current.getChildren()) {
			if (child.getEvalScore() == current.getEvalScore()){
				move = child.getMove();
			}
		}
		return move;
	}

	public void deleteTree() {
		this.root = null;
	}
}
