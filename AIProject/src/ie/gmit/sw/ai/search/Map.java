package ie.gmit.sw.ai.search;

import java.util.Collections;
import java.util.LinkedList;

import ie.gmit.sw.ai.GameModel;

public class Map {
	
	private static LinkedList<Node> queue = new LinkedList<Node>();
	private int nodeCount = 0;
	private LinkedList<Node> nodes = new LinkedList<Node>();

	public Map(GameModel map){

		for (int i = 0; i < map.size(); i++) {
			// System.out.println(nodes.toString());
			for (int j = 0; j < map.size(); j++) {
				// System.out.print( this.model.get(i,j));
				if (map.get(i, j) == '0') {
					// System.out.println("Hedge"+this.model.get(i, j));
				}

				else {
					//System.out.println("Not Hedge"+this.model.get(i, j));
					Node tempNode = new Node(String.valueOf(nodeCount));
					//System.out.println(this.model.get(i,j));
					if(map.get(i, j) == '1') {

						
						Node goal = new Node(String.valueOf(1+nodeCount));
						goal.setGoalNode(true);
						this.nodes.add(goal);
						System.out.println("Goal: "+tempNode);						
					}
					
					else {
						// System.out.println(nodes.toString());
						this.nodes.add(tempNode);
						if (i <= map.size() - 1 && 1+j <= map.size() - 1 && map.get(i, j+1) == ' ') {
							Node nextNode = new Node(String.valueOf(1+this.nodeCount));
							tempNode.addChildNode(nextNode);
						}
						if (1+i <= map.size() - 1 && j <= map.size() - 1 && map.get(1+i, j) == ' ') {
							Node nextNode = new Node(String.valueOf(1+this.nodeCount));
							tempNode.addChildNode(nextNode);
						}
						
						nodes.removeAll(Collections.singleton(null));
						//s = filteredNodes.get(nodeCount);
						this.nodeCount++;
						
					}
					
					
					
					BFS.search(tempNode);
					
					//System.out.println("TEst " + nodes);
				}
				
				

			}
		}
	}
}
