package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import ie.gmit.sw.ai.search.BFS;
import ie.gmit.sw.ai.search.Node;
import javafx.concurrent.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/*
 * CharacterTask represents a Runnable (Task is a JavaFX implementation
 * of Runnable) game character. The character wanders around the game
 * model randomly and can interact with other game characters using
 * implementations of the Command interface that it is composed with. 
 * 
 * You can change how this class behaves is a number of different ways:
 * 
 * 1) DON'T CHANGE THIS CLASS
 * 	  Configure the class constructor with an instance of Command. This can
 *    be a full implementation of a fuzzy controller or a neural network. You
 *    can also parameterise this class with a lambda expression for the 
 *    command object. 
 *    
 * 2) CHANGE THIS CLASS 
 * 	  You can extend this class and provide different implementations of the 
 * 	  call by overriding (not recommended). Alternatively, you can change the
 *    behaviour of the game character when it moves by altering the logic in
 *    the IF statement:  
 *    
 * 		if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
 * 	    	//Implementation for moving to an occupied cell
 * 	    }else{
 *      	//Implementation for moving to an unoccupied cell
 *      } 
 */

public class CharacterTask extends Task<Void> {
	private static final int SLEEP_TIME = 300; // Sleep for 300 ms
	private static ThreadLocalRandom rand = ThreadLocalRandom.current();
	private boolean alive = true;
	private GameModel model;
	private char enemyID;
	private int row;
	private int col;
	private boolean test = true;
	private Node s;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private LinkedList<Node> filteredNodes = new LinkedList<Node>();
	private int nodeCount = 0;
	private String nodeName = "0";

	/*
	 * Configure each character with its own action. Use this functional interface
	 * as a hook or template to connect to your fuzzy logic and neural network. The
	 * method execute() of Command will execute when the Character cannot move to a
	 * random adjacent cell.
	 */
	private Command cmd;

	public CharacterTask(GameModel model, char enemyID, int row, int col, Command cmd) {
		this.model = model;
		this.enemyID = enemyID;
		this.row = row;
		this.col = col;
		this.cmd = cmd;
	}

	@Override
	public Void call() throws Exception {
		/*
		 * This Task will remain alive until the call() method returns. This cannot
		 * happen as long as the loop control variable "alive" is set to true. You can
		 * set this value to false to "kill" the game character if necessary (or maybe
		 * unnecessary...).
		 */

		while (alive) {
			Thread.sleep(SLEEP_TIME);

			synchronized (model) {
				// Randomly pick a direction up, down, left or right

				if (test) {
					for (int i = 0; i < this.model.size(); i++) {
						// System.out.println(nodes.toString());
						for (int j = 0; j < this.model.size(); j++) {
							// System.out.print( this.model.get(i,j));
							if (model.get(i, j) == '0') {
								// System.out.println("Hedge"+this.model.get(i, j));
							}

							else {
								//System.out.println("Not Hedge"+this.model.get(i, j));
								Node tempNode = new Node(String.valueOf(nodeCount));
								//System.out.println(this.model.get(i,j));
								if(nodeCount == 154) {
									Node goal = new Node("t");
									goal.setGoalNode(true);
									this.nodes.add(goal);
									System.out.println("Goal: "+tempNode);
								}
								
								// System.out.println(nodes.toString());
								this.nodes.add(tempNode);
								if (model.isValidMove(i, j, i, j++, this.model.get(i, j))) {
									Node nextNode = new Node(String.valueOf(nodeCount++));
									tempNode.addChildNode(nextNode);
								}
								if (model.isValidMove(i, j, i++, j, this.model.get(i, j))) {
									Node nextNode = new Node(String.valueOf(nodeCount++));
									tempNode.addChildNode(nextNode);
								}
								
								filteredNodes.removeAll(Collections.singleton(null));
								//s = filteredNodes.get(nodeCount);
								nodeCount++;
								
								BFS.search(tempNode);
								
								//System.out.println("TEst " + nodes);
							}
							
							

						}
						test = false;
						
						// System.out.println("Test");
					}

					/// System.out.println(nodes.get(99));
				}

				System.out.println(nodes.toString());
				System.out.println(filteredNodes.toString());
				// System.out.println(nodes[i]);

				int temp_row = row, temp_col = col;
				if (rand.nextBoolean()) {
					temp_row += rand.nextBoolean() ? 1 : -1;
				} else {
					temp_col += rand.nextBoolean() ? 1 : -1;
				}

				if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
					/*
					 * This fires if the character can move to a cell, i.e. if it is not already
					 * occupied. You can add extra logic here to invoke behaviour when the computer
					 * controlled character is in the proximity of the player or another
					 * character...
					 */
					model.set(temp_row, temp_col, enemyID);
					model.set(row, col, '\u0020');
					row = temp_row;
					col = temp_col;
				} else {
					/*
					 * This fires if a move is not valid, i.e. if someone or some thing is in the
					 * way. Use implementations of Command to control how the computer controls this
					 * character.
					 */
					cmd.execute();
				}
			}
		}
		return null;
	}
}