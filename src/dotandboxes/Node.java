package dotandboxes;

import java.util.ArrayList;

public class Node {
	int depth;
	Node parent;
	ArrayList<Node> children;
	boolean max;
	String[][] state;
	int score;
	ArrayList<int[]> notClaimed;
	
	
	public Node(String[][] state) {
		this.state = state;
		
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


	public void setChildren(ArrayList<Node> children) {
		this.children = children;
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


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public ArrayList<int[]> getNotClaimed() {
		return notClaimed;
	}


	public void setNotClaimed(ArrayList<int[]> notClaimed) {
		this.notClaimed = notClaimed;
	}
	
	

}
