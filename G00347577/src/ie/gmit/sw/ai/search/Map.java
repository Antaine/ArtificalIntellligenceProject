package ie.gmit.sw.ai.search;

import java.util.Collections;
import java.util.LinkedList;

import ie.gmit.sw.ai.GameModel;

public class Map {
	private int nodeCount = 0;
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private static int[]target = new int[2];

	public Map(GameModel map) {

		for (int i = 0; i < map.size(); i++) {
			// System.out.println(nodes.toString());
			for (int j = 0; j < map.size(); j++) {
				// System.out.print( this.model.get(i,j));
				if (map.get(i, j) == '0') {
					// System.out.println("Hedge"+this.model.get(i, j));
				}

				else {
					// System.out.println("Not Hedge"+this.model.get(i, j));
					Node tempNode = new Node(String.valueOf(nodeCount));
					// System.out.println(this.model.get(i,j));
					if (map.get(i, j) == '1') {

						Node goal = new Node(String.valueOf(1 + nodeCount));
						//System.out.println(i+" "+j);
						goal.setGoalNode(true);
						this.setTarget(i, j);
						this.nodes.add(goal);
						System.out.println("Goal: " + tempNode);
					}

					else {
						// System.out.println(nodes.toString());
						this.nodes.add(tempNode);
						if (i <= map.size() - 1 && 1 + j <= map.size() - 1 && map.get(i, j + 1) == ' ') {
							Node nextNode = new Node(String.valueOf(1 + this.nodeCount));
							tempNode.addChildNode(nextNode);
						}
						if (1 + i <= map.size() - 1 && j <= map.size() - 1 && map.get(1 + i, j) == ' ') {
							Node nextNode = new Node(String.valueOf(1 + this.nodeCount));
							tempNode.addChildNode(nextNode);
						}

						this.nodes.removeAll(Collections.singleton(null));
						// s = filteredNodes.get(nodeCount);
						this.nodeCount++;

					}
					BFS.search(tempNode);
				}
			}
		}
	}
	public void setTarget(int row, int col) {
		this.target[0] = row;
		this.target[1] = col;
	}
	
	public int getRow() {
		return this.target[0];
	}
	
	public int getCol() {
		return this.target[1];
	}
}


