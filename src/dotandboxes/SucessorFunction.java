package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class SucessorFunction {
	
	
	public SucessorFunction(){
		
	}
	
	public ArrayList<Node> getSucessors(Node node) {
		ArrayList<Node> sucessors = new ArrayList<Node>();
		String[][] state = node.getState();
		
		
		for(int i = 1;i<state.length;i++) {
			for (int j = 1; j<state[0].length;j++) {
				if(state[i][j] == " ") {
					Node newNode = new Node(this.add(state, i, j));
					newNode.setDepth(node.depth+1);
					newNode.setParent(node);
					
				}
			}
		}
		
		return null;
		
	}
	
	private String[][] add(String[][] state,int index1, int index2) {
		if (index2 % 2 == 0) {
			state[index1][index2] ="-";
		}
		else {
			state[index1][index2] ="|";
		}
		
		return state;
		
	}
	
	private int checkScore(String[][] state,Node node) {
		int score = 0;
		ArrayList<int[]> notClaimed = node.getNotClaimed();
		Iterator<int[]> iter = notClaimed.iterator();
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
