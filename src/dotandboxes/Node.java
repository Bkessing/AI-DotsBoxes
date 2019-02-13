package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	int depth;
	int score;
	boolean max;
	Node parent;
	ArrayList<Node> children;
	String[][] state;
	ArrayList<int[]> yourMoves;
	ArrayList<int[]> opponentMoves;
	
	
	public Node(String[][] state) {
		this.state = state;
		this.score = 0;
		
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


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	public void addMove(int[] move) {
		this.yourMoves.add(move);
	}
	
	public void addOpponentMove(int[] move) {
		this.opponentMoves.add(move);
	}
	
	public ArrayList<int[]> getMoves(){
		return this.yourMoves;
	}
	
	public ArrayList<int[]> getOpponentMoves(){
		return this.opponentMoves;
	}



	public void eval() {
	 int[][] boxes = new int[][] {{2,2},{2,4},{4,2},{4,4}};
	String[][] newState = new String[6][6];
		
		
	 for(int[] box : boxes) {
		 if(state[box[0]+1][box[1]] != "a" && state[box[0]-1][box[1]] != "a" && state[box[0]][box[1]+1] != "a" && state[box[0]][box[1]-1] != "a") {
			 this.score = this.score + Integer.decode(newState[box[0]][box[1]]);
		 }
	 }
	
	}
	
}
