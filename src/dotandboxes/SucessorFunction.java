package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class SucessorFunction {
	Node node;

	public SucessorFunction() {

	}

	public ArrayList<Node> getSucessors(Node node) {
		this.node = node;

		ArrayList<Node> sucessors = new ArrayList<Node>();
		String[][] state = node.getState();

		for (int i = 1; i < state.length; i++) {
			for (int j = 1; j < state[0].length; j++) {
				if (state[i][j] == " ") {
					Node newNode = new Node(this.add(state, i, j));
					newNode.setMove(new int[] { i, j });
					newNode.setDepth(node.depth + 1);
					newNode.setParent(node);
					newNode.setNotClaimed(this.copy(node.getNotClaimed()));
					if (node.isMax()) {
						newNode.setMax(false);
						newNode.evalScore = this.eval(newNode, node.evalScore);
					} else {
						newNode.setMax(true);
						int evalScore = this.eval(newNode, node.evalScore);
						newNode.evalScore = evalScore;
					}
					sucessors.add(newNode);

				}
			}
		}

		return sucessors;

	}

	private String[][] add(String[][] state, int index1, int index2) {

		String[][] newState = new String[6][6];

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				newState[i][j] = state[i][j];
			}
		}
		if (index2 % 2 == 0) {
			newState[index1][index2] = "-";
		} else {
			newState[index1][index2] = "|";
		}

		return newState;

	}

	public int eval(Node newNode, int evalScore) {
		String[][] state = newNode.getState();

		Iterator<int[]> iter = newNode.getNotClaimed().iterator();
		while (iter.hasNext()) {
			int[] i = iter.next();
			if (state[i[0] + 1][i[1]] != " " && state[i[0] - 1][i[1]] != " " && state[i[0]][i[1] + 1] != " "
					&& state[i[0]][i[1] - 1] != " ") {
			
				if(newNode.isMax()) {
					evalScore = evalScore - Integer.decode(state[i[0]][i[1]]);
				}
				else {
					evalScore = evalScore + Integer.decode(state[i[0]][i[1]]);
				}
	
				iter.remove();
				

			}

		

		}
		

		return evalScore;

	}

	private ArrayList<int[]> copy(ArrayList<int[]> toCopy) {
		ArrayList<int[]> copy = new ArrayList<int[]>();

		for (int i = 0; i < toCopy.size(); i++) {
			copy.add(new int[] { toCopy.get(i)[0], toCopy.get(i)[1] });
		}
		return copy;

	}

}
