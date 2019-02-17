package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	int depth;
	boolean max;
	Node parent;
	ArrayList<Node> children;
	String[][] state;
	int[] move;
	ArrayList<int[]> notClaimed;
	int evalScore;

	public Node(String[][] state) {
		this.state = state;
		this.depth = 0;
		children = new ArrayList<Node>();
		move = new int[2];

	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public boolean isMax() {
		return max;
	}

	public void setMax(boolean max) {
		this.max = max;
	}

	public String[][] getState() {
		return state;
	}

	public void setState(String[][] state) {
		this.state = state;
	}

	public ArrayList<int[]> getNotClaimed() {
		return this.notClaimed;
	}

	public void setNotClaimed(ArrayList<int[]> notClaimed) {
		this.notClaimed = notClaimed;
	}

	public int[] getMove() {
		return move;
	}

	public void setMove(int[] move) {
		this.move = move;
	}

	public int getEvalScore() {
		return evalScore;
	}

	public void setEvalScore(int evalScore) {
		this.evalScore = evalScore;
	}

	public void print() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println(" ");
		}

		System.out.println(this.max);
	}

	public void printClaimed() {
		for (int i = 0; i < notClaimed.size(); i++) {
			for (int j = 0; j < notClaimed.get(0).length; j++) {
				System.out.print(notClaimed.get(i)[j] + " ,");
			}
		}
		System.out.println("");
	}

}
