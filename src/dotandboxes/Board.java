package dotandboxes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/*
 * This is the main game board, this keeps the current state of the game and can check for wins and loses.
 */
public class Board {
	int[] values;
	int numOfMoves;
	String[][] state;
	ArrayList<int[]> connected;
	ArrayList<int[]> notClaimed;
	boolean player1Turn;
	boolean player2Turn;
	boolean playing;
	Scanner reader;
	Player player;
	AI ai;

	public Board(String[][] startState,int numOfMoves) {
		this.state = startState;
		this.connected = new ArrayList<int[]>();
		this.notClaimed = new ArrayList<int[]>();
		this.reader = new Scanner(System.in);
		this.numOfMoves = numOfMoves;
		this.getRandoms(state);
		this.player1Turn = true;
		this.player2Turn = false;

	}
	// connect() takes a index into the state of the board from the player or the AI, it add the according char to it ie. - or _
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
				// updates player's move list
				player.addMove(connect);

			} else {
				// updates ai's move list
				ai.addMove(connect);
			}
			this.connected.add(connect);
		} else {
			// if space is not a valid space or space is filled throw an exception
			throw new IllegalArgumentException();
		}
	}

	// getRandoms() inserts random numbers between 1 - 5 into the center of all boxes on the board.
	private void getRandoms(String[][] state) {
		Random r = new Random();
		for (int i = 2; i < state.length; i = i + 2) {
			for (int j = 2; j < state.length; j = j + 2) {
				Integer x = new Integer(r.nextInt(4) + 1);
				state[i][j] = x.toString();
				// adds all center locations into the not claimed boxes list
				notClaimed.add(new int[] { i, j });
			}
		}

	}
	// print() just prints some logistics of the game state and scores and prints the game board.
	private void print() {
		System.out.print("Player1's lines: ");
		for (int i = 0; i < player.getMoves().size(); i++) {
			for (int j = 0; j < player.getMoves().get(0).length; j++) {
				System.out.print(player.getMoves().get(i)[j]);
				if (j == 0) {
					System.out.print(",");
				}
			}
			System.out.print(" ");

		}

		System.out.println("");
		System.out.println("Player1's Score: " + player.getScore());

		System.out.print("Player2's lines: ");
		for (int i = 0; i < ai.getMoves().size(); i++) {
			for (int j = 0; j < ai.getMoves().get(0).length; j++) {
				System.out.print(ai.getMoves().get(i)[j]);
				if (j == 0) {
					System.out.print(",");
				}
			}
			System.out.print(" ");

		}

		System.out.println("");
		System.out.println("Player2's Score: " + ai.getScore());

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println(" ");
		}

	}

	// start() is the game loop that switches players each turn and updates the number of moves left
	// also calls for 
	public void start(AI ai, Player player) {
		this.player = player;
		this.ai = ai;
		playing = true;
		while (playing) {
			if (player1Turn) {
				this.print();
				try {
					this.connect(player.takeTurn());
				} catch (Exception e) {
					continue;
				}
				this.checkForWin();
				this.numOfMoves--;
				this.player1Turn = false;
				this.player2Turn = true;

			} else {
				this.takeAiTurn(this.ai);
				this.checkForWin();
				this.numOfMoves--;
				this.player2Turn = false;
				this.player1Turn = true;

			}

		}

	}

	// checkForWins() this functions checks the updated state of the board, if the last move closed the box it removes that box from notClaimed list and updates the players score
	// according to the value of that box. It also check for the end of the game.
	private void checkForWin() {

		Iterator<int[]> iter = notClaimed.iterator();
		while (iter.hasNext()) {
			int[] i = iter.next();
			if (state[i[0] + 1][i[1]] != " " && state[i[0] - 1][i[1]] != " " && state[i[0]][i[1] + 1] != " "
					&& state[i[0]][i[1] - 1] != " ") {
				if (player1Turn) {
					player.addScore(Integer.decode(state[i[0]][i[1]]));
				} else {
					ai.addScore(Integer.decode(state[i[0]][i[1]]));
				}
				iter.remove();

			}
			if (notClaimed.size() == 0) {
				this.print();
				playing = false;
			}

		}
	}
	// sets up the starting node with the current values of the board
	// then builds the ai tree and finds the best move
	private void takeAiTurn(AI ai) {
		Node startNode = new Node(this.state);
		startNode.setNotClaimed(this.copy(this.notClaimed));
		startNode.setMax(true);
		startNode.setEvalScore(ai.getScore() - player.getScore());
		Stack<Node> tree = ai.buildTree(startNode, this.numOfMoves);
		int[] move = ai.findTurn(tree);
		String line = move[0] + "," + move[1];
		this.connect(line);
		// delete tree so heap doesn't run of out memory
		ai.deleteTree();

	}
	// aux function to copy lists so we don't reference the current board states
	private ArrayList<int[]> copy(ArrayList<int[]> toCopy) {
		ArrayList<int[]> copy = new ArrayList<int[]>();

		for (int i = 0; i < toCopy.size(); i++) {
			copy.add(new int[] { toCopy.get(i)[0], toCopy.get(i)[1] });
		}
		return copy;

	}

}
