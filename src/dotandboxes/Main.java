package dotandboxes;



public class Main {

	public static void main(String[] args) {
		String[][] startState = new String[][] {{" ","1","2","3","4","5"},{"1","*"," ","*"," ","*"},{"2"," "," "," "," "," "},{"3","*"," ","*"," ","*"},{"4"," "," "," "," "," "},{"5","*"," ","*"," ","*"}};
		
	
		
		
		
		
		Board b = new Board(startState);
		b.start();
		
	}

}
