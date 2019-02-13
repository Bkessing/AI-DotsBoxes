package dotandboxes;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//String[][] startState = new String[][] {{" ","1","2","3","4","5"},{"1","*"," ","*"," ","*"},{"2"," "," "," "," "," "},{"3","*"," ","*"," ","*"},{"4"," "," "," "," "," "},{"5","*"," ","*"," ","*"}};
		
		String[][] startState = new String[][] {{" ","1","2","3","4","5"},{"1","*","1","*","2","*"},{"2"," "," "," "," "," "},{"3","*","4","*","5","*"},{"4"," "," "," "," "," "},{"5","*"," ","*"," ","*"}};
		
		
		
		
		
		//Board b = new Board(startState);
		//b.start();
		
		AI ai = new AI();
		
		Node node = new Node(startState);
		node.setMax(true);
		ai.buildTree(node);
	}

}
