package dotandboxes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Board {
	int[] values;
	String[][] state;
	ArrayList<int[][]> connected;
	boolean player1Turn;
	boolean player2Turn;
	boolean playing;
	Scanner reader;

	public Board(String[][] startState) {
		this.state = startState;
		this.getRandoms(state);
		this.connected = new ArrayList<int[][]>();
		this.reader = new Scanner(System.in);

		player1Turn = true;
		player2Turn = false;
		
		

	}

	public void connect(String line) {
		line = line.replaceAll("(","");
		line = line.replaceAll(")","");
		line = line.replaceAll(","," ");
		String[] temp = line.split(",");
		int index1 = Integer.decode(temp[0]);
		int index2 = Integer.decode(temp[1]);
		int index3 = Integer.decode(temp[2]);
		int index4 = Integer.decode(temp[3]);
		
		
		this.connected.add(new int[][] {{index1, index2},{index3,index4}});

	}

	private int[] getRandoms(String[][] state) {
		Random r = new Random();

		for (int i = 0; i < values.length; i++) {
			values[i] = r.nextInt(4) + 1;
		}

		return values;

	}

	public void print() {

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println(" ");
		}

	}
	
	public void start() {
		playing = true;
		while(playing){
			if(player1Turn) {
				this.takeTurn("Player1");
				
				
			}
			else {
				this.takeTurn("Player2");
				
			}
			
			
			
		}
		
		
		
	}
	
	private void takeTurn(String player) {
		this.print();
		System.out.println(player +"'s turn");
		System.out.println("Enter space to put line using format: y,x ");
		String line = reader.next();
		
		
		
		
	}
	
	

}
