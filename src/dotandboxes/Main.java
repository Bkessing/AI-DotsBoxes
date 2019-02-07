package dotandboxes;



public class Main {

	public static void main(String[] args) {
		String[][] startState = new String[][] {{" ","1","2","3","4","5"},{"1","*","-","*"," ","*"},{"2","|"," "," "," "," "},{"3","*"," ","*"," ","*"},{"4"," "," "," "," "," "},{"5","*"," ","*"," ","*"}};
		
		for(int i = 0;i<startState.length;i++) {
			for(int j = 0;j<startState[0].length;j++) {
				
				System.out.print(startState[i][j]+ " ");
				
			}
			System.out.println(" ");
			
		}
		
		
		
		
		
		//Board b = new Board();
		
	}

}
