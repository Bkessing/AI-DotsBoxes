package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Board {
	int[] values;
	String[][] state;
	ArrayList<int[]> connected;
	ArrayList<int[]> p1Connected;
	ArrayList<int[]> p2Connected;
	ArrayList<int[]> notClaimed;
	boolean player1Turn;
	boolean player2Turn;
	boolean playing;
	int p1Score;
	int p2Score;
	Scanner reader;
	AI ai;
	AI ai2;
	int numOfMoves;

	public Board(String[][] startState) {
		this.state = startState;
		this.connected = new ArrayList<int[]>();
		this.p1Connected = new ArrayList<int[]>();
		this.p2Connected = new ArrayList<int[]>();
		this.notClaimed = new ArrayList<int[]>();
		this.reader = new Scanner(System.in);
		this.numOfMoves = 12;

		this.getRandoms(state);
		this.ai = new AI();
		this.ai2 = new AI();

		player1Turn = true;
		player2Turn = false;

		p1Score = 0;
		p2Score = 0;

	}

	public void connect(String line) {
		String[] temp = line.split(",");
		int index1 = Integer.decode(temp[0]);
		int index2 = Integer.decode(temp[1]);
		if (state[index1][index2] == " ") {
			if (index2 % 2 == 0) {
				state[index1][index2] = "-";
			} else {
				state[index1][index2] = "|";
			}
			int[] connect = new int[] { index1, index2 };

			if (player1Turn) {
				p1Connected.add(connect);

			} else {
				p2Connected.add(connect);
			}
			this.connected.add(connect);
		} else {
			System.out.println("Invalid");
			this.takeTurn();
		}
	}

	private void getRandoms(String[][] state) {
		Random r = new Random();
		for (int i = 2; i < 5; i = i + 2) {
			for (int j = 2; j < 5; j = j + 2) {
				Integer x = new Integer(r.nextInt(4) + 1);
				state[i][j] = x.toString();
				notClaimed.add(new int[] { i, j });
			}
		}

	}

	public void print() {

		System.out.print("Player1's lines: ");
		for (int i = 0; i < p1Connected.size(); i++) {
			for (int j = 0; j < p1Connected.get(0).length; j++) {
				System.out.print(p1Connected.get(i)[j]);
				if (j == 0) {
					System.out.print(",");
				}
			}
			System.out.print(" ");

		}

		System.out.println("");
		System.out.println("Player1's Score: " + p1Score);

		System.out.print("Player2's lines: ");
		for (int i = 0; i < p2Connected.size(); i++) {
			for (int j = 0; j < p2Connected.get(0).length; j++) {
				System.out.print(p2Connected.get(i)[j]);
				if (j == 0) {
					System.out.print(",");
				}
			}
			System.out.print(" ");

		}

		System.out.println("");
		System.out.println("Player2's Score: " + p2Score);

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println(" ");
		}

	}

	public void start() {
		playing = true;
		while (playing) {
			if (player1Turn) {
				this.takeTurn();
				// this.takeAiTurn(this.ai2);
				this.checkForWin();
				this.numOfMoves--;
				this.player1Turn = false;
				this.player2Turn = true;
				// this.print();

			} else {
				this.takeAiTurn(this.ai);
				this.checkForWin();
				this.numOfMoves--;
				this.player2Turn = false;
				this.player1Turn = true;
				// this.print();

			}

		}

	}

	private void takeTurn() {
		this.print();
		System.out.println("Enter space to put line using format: y,x ");
		String line = reader.next();
		this.connect(line);

	}

	private void checkForWin() {

		Iterator<int[]> iter = notClaimed.iterator();
		while (iter.hasNext()) {
			int[] i = iter.next();
			if (state[i[0] + 1][i[1]] != " " && state[i[0] - 1][i[1]] != " " && state[i[0]][i[1] + 1] != " "
					&& state[i[0]][i[1] - 1] != " ") {
				if (player1Turn) {
					p1Score = p1Score + Integer.decode(state[i[0]][i[1]]);
				} else {
					p2Score = p2Score + Integer.decode(state[i[0]][i[1]]);
				}
				iter.remove();

			}
			if (notClaimed.size() == 0) {
				this.print();
				playing = false;
			}

		}
	}

	private void takeAiTurn(AI ai) {
		Node startNode = new Node(this.state);
		startNode.setNotClaimed(this.copy(this.notClaimed));
		startNode.setMax(true);
		startNode.setEvalScore(this.p2Score - this.p1Score);
		Stack<Node> tree = ai.buildTree(startNode, this.numOfMoves);
		int[] move = ai.findTurn(tree);
		String line = move[0] + "," + move[1];
		this.connect(line);
		ai.deleteTree();

	}

	private ArrayList<int[]> copy(ArrayList<int[]> toCopy) {
		ArrayList<int[]> copy = new ArrayList<int[]>();

		for (int i = 0; i < toCopy.size(); i++) {
			copy.add(new int[] { toCopy.get(i)[0], toCopy.get(i)[1] });
		}
		return copy;

	}

}
