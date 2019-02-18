package dotandboxes;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	int score;
	ArrayList<int[]> moves;
	Scanner reader;

	public Player() {
		score = 0;
		moves = new ArrayList<int[]>();
		reader = new Scanner(System.in);

	}
	// takeTurn() takes user input as to what line you want to place on the board
	public String takeTurn() {
		System.out.println("Enter space to put line using format: y,x ");
		String line = reader.next();
		return line;
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
