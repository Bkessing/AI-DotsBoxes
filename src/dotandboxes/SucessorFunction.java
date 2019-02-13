package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class SucessorFunction {
	Node node;
	ArrayList<int[]> notClaimed;
	
	public SucessorFunction(){
		
	}
	
	public ArrayList<Node> getSucessors(Node node) {
		this.node = node;
		
		ArrayList<Node> sucessors = new ArrayList<Node>();
		String[][] state = node.getState();
		
		
		for(int i = 1;i<state.length;i++) {
			for (int j = 1; j<state[0].length;j++) {
				if(state[i][j] == " ") {
					Node newNode = new Node(this.add(state, i, j));
					newNode.setDepth(node.depth+1);
					newNode.setParent(node);
					sucessors.add(newNode);
					if(node.isMax()) {
						newNode.setMax(false);
					}
					else {
						newNode.setMax(true);
					}
					
				}
			}
		}
		
		return sucessors;
		
	}
	
	private String[][] add(String[][] state,int index1, int index2) {
		
		String[][] newState = new String[6][6];
		
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				newState[i][j] = state[i][j];
			}
}
		if (this.node.isMax()) {
			newState[index1][index2] ="a";
		}
		else {
			newState[index1][index2] ="o";
		}
		
		return newState;
		
	}
	
	private int checkScore(String[][] state) {
		int score = 0;
		Iterator<int[]> iter = this.notClaimed.iterator();
		while(iter.hasNext()) {
			int [] i = iter.next();
			if(state[i[0]+1][i[1]] != " " && state[i[0]-1][i[1]] != " " && state[i[0]][i[1]+1] != " " && state[i[0]][i[1]-1] != " ") {
				score = score + Integer.decode(state[i[0]][i[1]]);
				iter.remove();
				
			}
		
	}
		return score;
	}
	

}
