package dotandboxes;

public class AI {
	int maxDepth;
	int[] optimalTurn;
	SucessorFunction sucessorFunction;
	
	public AI() {
		this.sucessorFunction = new SucessorFunction();
	}
	
	public void buildTree(Node start) {
		//sucessorFunction.getSucessors(start, p1Connected, p2Connected);
		
	}

}
