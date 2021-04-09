package ie.gmit.sw.ai.search;
import java.util.*;

import ie.gmit.sw.ai.Colour;
import ie.gmit.sw.ai.GameModel;

public class BFS {
	private LinkedList<Node> queue = new LinkedList<Node>();
	
	public BFS(char[][] cs) {
		//Node start = cs.getStartNode();
		//start.colour(Colour.Black);
	//	queue.addLast(start);
		//search(start);
	}
	
	public void search(Node node){
		while(!queue.isEmpty()){
			if (node.isGoalNode()){
				System.out.println("Reached goal node " + node.getNodeName());
				System.exit(0);
			}else{
				System.out.println(queue);
				Node[] children = node.children();
				queue.removeFirst();
				for (int i = 0; i < children.length; i++) {
				//for (int i = children.length - 1; i >= 0; i--) {
					if (children[i].getColour() == Colour.White){
						queue.addLast(children[i]);
						children[i].colour(Colour.Grey);
					}
				}
			}
			node = queue.getFirst();
			node.colour(Colour.Black);
		}
	}

	public static void main(String[] args) {
		//model = GameModel.getInstance();
		//new BFS(GameModel.getModel());
	//	System.out.println(model[0][0]);
		//BFS search = new BFS(model);
	}
}
