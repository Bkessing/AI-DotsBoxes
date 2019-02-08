package dotandboxes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Board {
	int[] values;
	String[][] state;
	ArrayList<int[]> connected;
	boolean player1Turn;
	boolean player2Turn;
	boolean playing;
	Scanner reader;

	public Board(String[][] startState) {
		this.state = startState;
		this.getRandoms(state);
		this.connected = new ArrayList<int[]>();
		this.reader = new Scanner(System.in);

		player1Turn = true;
		player2Turn = false;
		
		

	}

	public void connect(String line) {
		String[] temp = line.split(",");
		int index1 = Integer.decode(temp[0]);
		int index2 = Integer.decode(temp[1]);
		if (index2 % 2 == 0) {
			state[index1][index2] ="-";
		}
		else {
			state[index1][index2] ="|";
		}
		
		
		this.connected.add(new int[] {index1, index2});

	}

	private void getRandoms(String[][] state) {
		Random r = new Random();
		for(int i = 2;i<5;i = i+2) {
			for(int j = 2;j<5;j = j+2) {
		 Integer x = new Integer(r.nextInt(4) + 1);
		 state[i][j] = x.toString();
			}
		}


	}

	public void print() {

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				System.out.print(state[i][j] + " " );
			}
			System.out.println(" ");
		}

	}
	
	public void start() {
		playing = true;
		while(playing){
			if(player1Turn) {
				this.takeTurn("Player1");
				this.player1Turn = false;
				this.player2Turn = true;
				this.print();
				
				
			}
			else {
				this.takeTurn("Player2");
				this.player2Turn = false;
				this.player1Turn = true;
				this.print();
				
			}
			
			
			
		}
		
		
		
	}
	
	private void takeTurn(String player) {
		this.print();
		System.out.println(player +"'s turn");
		System.out.println("Enter space to put line using format: y,x ");
		String line = reader.next();
		this.connect(line);
			
	}
	
	

}
