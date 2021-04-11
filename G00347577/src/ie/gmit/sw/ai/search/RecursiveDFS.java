package ie.gmit.sw.ai.search;

import ie.gmit.sw.ai.GameModel;

public class RecursiveDFS {
	
	private Node node;
	
	public RecursiveDFS(GameModel maze) {

	//	search(maze.getStartNode());	
	//	search(maze.getStartNode());		
	}

	public void search(Node node){
		if (node.isGoalNode()){
			System.out.println("Reached goal node " + node.getNodeName());
			System.exit(0);
		}else{
			if (!node.isVisited()){
				node.setVisited(true);
				System.out.println(node);
				Node[] children = node.children();
				for (int i = 0; i < children.length; i++) {
					search(children[i]);
				}
			}
		}
	}

	public static void main(String[] args) {
	//	Maze maze = Maze.getInstance();
	//	RecursiveDFS search = new RecursiveDFS(maze);
	}
}
