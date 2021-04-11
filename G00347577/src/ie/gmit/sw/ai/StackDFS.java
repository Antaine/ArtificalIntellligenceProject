package ie.gmit.sw.ai;

import java.util.*;

import ie.gmit.sw.ai.search.Node;
public class StackDFS {
	private LinkedList<Node> deque = new LinkedList<Node>();
	
	public StackDFS(GameModel maze) {
		Node start = maze.getStartNode();
		deque.push(start);
		search();
	}

	public void search(){
		while(!deque.isEmpty()){
			Node node = deque.pop();
			node.setVisited(true);
			System.out.println(deque);
			System.out.println(node);
			if (node.isGoalNode()){
				System.out.println("Reached goal node " + node.getNodeName());
				System.exit(0);
			}else{
				Node[] children = node.children();
				for (int i = 0; i <children.length; i++) {
					if (!children[i].isVisited()){
						deque.push(children[i]);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		//new StackDFS(GameModel.getInstance());
	}
}
