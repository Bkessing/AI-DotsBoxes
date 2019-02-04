package dotandboxes;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Board {
	int[] values;
	ArrayList<Integer> selections;
	ArrayList<int[]> connected;
	boolean player1Turn;
	boolean player2Turn;
	
	public Board() {
		connected = new ArrayList<int[]>();
		selections = new ArrayList<Integer>();
		this.values = getRandoms(new int[8]);
		
		player1Turn = false;
		player2Turn = false;
		this.start();
		
	}
	
	private void start() {
		JFrame window = new JFrame();
		DrawingComponent dc = new DrawingComponent();	
		window.setSize(430,430);
		window.setTitle("Dots and Boxes");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(dc);		
		window.setVisible(true);
		
	}
	
	public void connect(int index1,int index2) {
		this.connected.add(new int[]{index1,index2});
		
	}
	
	private int[] getRandoms(int[] values) {
		Random r = new Random();
		
		for(int i = 0;i<values.length;i++) {
			values[i] = r.nextInt(4) + 1;
		}
		
		return values;
				
		
		
	}
	
	public void addSelection(int selection) {
		selections.add(selection);
	}
	
	public ArrayList<Integer> getSelection() {
		return selections;
	}

}
