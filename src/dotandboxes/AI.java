package dotandboxes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AI {
	int maxDepth;
	int[] optimalTurn;
	SucessorFunction successorFunction;
	Node root;
	int score;
	ArrayList<int[]> moves;

	public AI(int ply) {
		this.score = 0;
		this.moves = new ArrayList<int[]>();
		this.successorFunction = new SucessorFunction();
		this.maxDepth = ply;
		optimalTurn = new int[2];
		this.root = null;
	}
	
	// buildTree() creates the tree according to how many plys the user wants. 
	public Stack<Node> buildTree(Node start, int numOfMoves) {
		// stack used to reverse the tree later
		Stack<Node> stack = new Stack<Node>();
		// queue used to build tree
		Queue<Node> queue = new LinkedList<Node>();
		Node current = start;

		while (current.getDepth() != maxDepth) {
			// hold all the successors of the current node
			ArrayList<Node> successors = successorFunction.getSucessors(current);
			
			// end of game board
			if (successors.size() == 0) {
				break;
			}
			// for each loop of the successors of the current node
			// add all children to child list
			// adds each the the queue
			for (Node successor : successors) {
				current.addChild(successor);
				queue.add(successor);
			}
			// if only one possible move add it to the stack
			if (numOfMoves == 1) {
				stack.push(current);
			}
			// if their are less possibles moves than the specified depth then go add deep at the number of moves allows 
			if (numOfMoves < this.maxDepth) {
				// go one above max depth in stack
				if (current.depth < this.maxDepth - 1) {
					stack.push(current);
				}
			} else {
				// go one above max depth in stack
				if (current.depth < numOfMoves - 1) {
					stack.push(current);
				}
			}
			current = queue.poll();
		}
		return stack;

	}

	// findTurn() takes the stack we created previous and percolates the final evalscore up to the stop node and chooses a move based on the max at the root
	// we only go to maxDepth -1 on the stack because we use the child list to find the max or min
	public int[] findTurn(Stack<Node> tree) {

		Node current = tree.peek();
		int[] move = new int[2];
		while (!tree.isEmpty()) {
			current = tree.pop();
			// sets random values of the childen for max and min
			int max = current.getChildren().get(0).evalScore;
			int min = current.getChildren().get(0).evalScore;
			for (Node child : current.getChildren()) {
				// if at a "max" depth check for max else check for min
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
			// set current nodes evalscore to percolated evalscore
			if (current.isMax()) {
				current.evalScore = max;
			} else {
				current.evalScore = min;
			}

		}
		// set root of the tree so we can set it to null later so the tree can be garbage collected
		root = current;
		// finds child of the root with the max score to choose the optimal move
		for (Node child : current.getChildren()) {
			if (child.getEvalScore() == current.getEvalScore()) {
				move = child.getMove();
			}
		}
		return move;
	}
	// sets root to null so the tree can be garbage collected
	public void deleteTree() {
		this.root = null;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score = this.score + score;
	}

	public ArrayList<int[]> getMoves() {
		return moves;
	}

	public void addMove(int[] move) {
		this.moves.add(move);
	}

}
