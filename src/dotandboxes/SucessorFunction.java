package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;

public class SucessorFunction {
	Node node;

	public SucessorFunction() {

	}
	// getSucessors gets all of the children of the node that is passed to it
	// created new nodes based on the possible children and adjusts the values to represent the score, depth, max, ect
	public ArrayList<Node> getSucessors(Node node) {
		this.node = node;

		ArrayList<Node> sucessors = new ArrayList<Node>();
		String[][] state = node.getState();
		// check for all possible moves
		for (int i = 1; i < state.length; i++) {
			for (int j = 1; j < state[0].length; j++) {
				if (state[i][j] == " ") {
					// created a new node with the previous state plus the added move
					Node newNode = new Node(this.add(state, i, j));
					// sets the move used to create node
					newNode.setMove(new int[] { i, j });
					//sets depth based on parent
					newNode.setDepth(node.depth + 1);
					// sets the child nodes parent
					newNode.setParent(node);
					// passed done a copy of the parents node not claimed boxes list
					newNode.setNotClaimed(this.copy(node.getNotClaimed()));
					// sets max or min 
					if (node.isMax()) {
						newNode.setMax(false);
					} else {
						newNode.setMax(true);
					}
					newNode.evalScore = this.eval(newNode, node.evalScore);
					sucessors.add(newNode);

				}
			}
		}

		return sucessors;

	}
	// add() created a copy of the parent nodes state and add the move the child node
	private String[][] add(String[][] state, int index1, int index2) {

		String[][] newState = new String[10][10];

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
	// eval() returns the net score of the node and updates the not claimed box list for the node
	public int eval(Node newNode, int evalScore) {
		String[][] state = newNode.getState();

		Iterator<int[]> iter = newNode.getNotClaimed().iterator();
		while (iter.hasNext()) {
			int[] i = iter.next();
			if (state[i[0] + 1][i[1]] != " " && state[i[0] - 1][i[1]] != " " && state[i[0]][i[1] + 1] != " "
					&& state[i[0]][i[1] - 1] != " ") {

				if (newNode.isMax()) {
					// Subtracts score if opponents move
					evalScore = evalScore - Integer.decode(state[i[0]][i[1]]);
				} else {
					// adds score if it is the ai's turn
					evalScore = evalScore + Integer.decode(state[i[0]][i[1]]);
				}

				iter.remove();

			}

		}

		return evalScore;

	}

	// copys arrays so we do not reference a previous array
	private ArrayList<int[]> copy(ArrayList<int[]> toCopy) {
		ArrayList<int[]> copy = new ArrayList<int[]>();

		for (int i = 0; i < toCopy.size(); i++) {
			copy.add(new int[] { toCopy.get(i)[0], toCopy.get(i)[1] });
		}
		return copy;

	}

}
