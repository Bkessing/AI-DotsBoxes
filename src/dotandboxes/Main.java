package dotandboxes;

import java.util.Scanner;
/*
 * Created by Brandon Kessinger
 */

public class Main {
	
	// Askes the user what size board they want and how many plys the AI should look at.

	public static void main(String[] args) {
		String[][] startState3 = new String[][] { { " ", "1", "2", "3" }, { "1", "*", " ", "*" },
				{ "2", " ", " ", " " }, { "3", "*", " ", "*" } };

		String[][] startState5 = new String[][] { { " ", "1", "2", "3", "4", "5" }, { "1", "*", " ", "*", " ", "*" },
				{ "2", " ", " ", " ", " ", " " }, { "3", "*", " ", "*", " ", "*" }, { "4", " ", " ", " ", " ", " " },
				{ "5", "*", " ", "*", " ", "*" } };

		String[][] startState7 = new String[][] { { " ", "1", "2", "3", "4", "5", "6", "7" },
				{ "1", "*", " ", "*", " ", "*", " ", "*" }, { "2", " ", " ", " ", " ", " ", " ", " " },
				{ "3", "*", " ", "*", " ", "*", " ", "*" }, { "4", " ", " ", " ", " ", " ", " ", " " },
				{ "5", "*", " ", "*", " ", "*", " ", "*" }, { "6", " ", " ", " ", " ", " ", " ", " " },
				{ "7", "*", " ", "*", " ", "*", " ", "*" } };

		Scanner reader = new Scanner(System.in);
		Board b = new Board(startState5,12);
		System.out.println("Welcome to Dots and Boxes!");
		System.out.println("Select from the following board sizes");
		System.out.println("1: 3x3");
		System.out.println("2: 5x5");
		System.out.println("3: 7x7");
		int d = reader.nextInt();

		System.out.println("Enter the number of plys you want the AI to look at: ");
		int n = reader.nextInt();

		switch (d) {

		case 1:
			b = new Board(startState3,4);
			break;

		case 2:
			b = new Board(startState5,12);
			break;

		case 3:
			b = new Board(startState7,24);
			break;
		}

		AI ai = new AI(n);
		Player player = new Player();

		b.start(ai, player);
		reader.close();

	}

}
