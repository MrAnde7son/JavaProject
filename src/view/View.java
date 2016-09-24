package view;

import algorithms.mazeGenerators.Maze3d;
/***
 * View in MVP architecture.
 * @author Itamar&Chen
 *
 */
public interface View {
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void displayMessage(String msg);	
	void start();	
}
