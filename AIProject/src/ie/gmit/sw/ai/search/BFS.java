package ie.gmit.sw.ai.search;
import java.util.*;

import ie.gmit.sw.ai.Colour;
import ie.gmit.sw.ai.GameModel;

public class BFS {
	private static LinkedList<Node> queue = new LinkedList<Node>();
	
	public BFS(GameModel model, char[][] maze) {
		Node start = model.getStartNode();
		start.colour(Colour.Black);
		queue.addLast(start);
		search(start);
	}
	
	
	public static void search(Node node){
		while(!queue.isEmpty()){
			System.out.println("Que: " + queue);
			if (node.isGoalNode()){
				//System.out.println("Reached goal node " + node.getNodeName());
				System.exit(0);
			}else{
				System.out.println("Que Loop "+queue);
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
}
